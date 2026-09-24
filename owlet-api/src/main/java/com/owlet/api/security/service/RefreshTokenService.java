package com.owlet.api.security.service;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.idm.RefreshSession;
import com.owlet.api.domain.idm.RefreshToken;
import com.owlet.api.repository.idm.RefreshSessionRepository;
import com.owlet.api.repository.idm.RefreshTokenRepository;
import com.owlet.api.security.dto.LoginResponse;
import com.owlet.api.security.jwt.JwtProperties;
import com.owlet.api.security.jwt.JwtService;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.HexFormat;
import java.util.Objects;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository tokens;
    private final RefreshSessionRepository sessions;
    private final JwtService jwtService;
    private final JwtProperties properties;
    private final EntityManager entityManager;
    private final TransactionTemplate transaction;
    private final SecureRandom random = new SecureRandom();

    public RefreshTokenService(
            RefreshTokenRepository tokens,
            RefreshSessionRepository sessions,
            JwtService jwtService,
            JwtProperties properties,
            EntityManager entityManager,
            PlatformTransactionManager transactionManager) {
        if (properties.getRefreshTokenExpirationDays() <= 0) {
            throw new IllegalArgumentException("Refresh token lifetime must be positive");
        }
        this.tokens = tokens;
        this.sessions = sessions;
        this.jwtService = jwtService;
        this.properties = properties;
        this.entityManager = entityManager;
        this.transaction = new TransactionTemplate(transactionManager);
        this.transaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        this.transaction.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        this.transaction.setTimeout(15);
    }

    // Call only after the existing password check succeeds. No public issue endpoint.
    public LoginResponse issue(Account verifiedAccount) {
        return issue(verifiedAccount, null, null);
    }

    public LoginResponse issue(Account verifiedAccount, String ipAddress, String userAgent) {
        UUID accountId = Objects.requireNonNull(verifiedAccount.getId());
        return Objects.requireNonNull(transaction.execute(status -> {
            Account account = entityManager.find(Account.class, accountId);
            if (account != null) entityManager.refresh(account);
            if (!allowed(account)) throw new InvalidRefreshTokenException();
            OffsetDateTime now = now();
            RefreshSession session = new RefreshSession();
            session.setAccount(account);
            session.setIssuedAt(now);
            session.setExpiredAt(now.plusDays(properties.getRefreshTokenExpirationDays()));
            session = sessions.save(session);
            return mint(account, session, now, ipAddress, userAgent);
        }));
    }

    public LoginResponse refresh(String rawToken, String ipAddress, String userAgent) {
        if (!wellFormed(rawToken)) throw new InvalidRefreshTokenException();
        String hash = hash(rawToken);
        LoginResponse result = transaction.execute(status -> {
            UUID sessionId = tokens.findSessionIdByHash(hash).orElse(null);
            if (sessionId == null) return null;
            // Every token in this session uses the SAME row lock.
            RefreshSession session = sessions.findLocked(sessionId).orElse(null);
            if (session == null) return null;
            // Load the token only after acquiring the family lock.
            RefreshToken token = tokens.findByHash(hash).orElse(null);
            if (token == null) return null;
            OffsetDateTime now = now();
            if (session.getRevokedAt() != null) return null;
            if (!session.getExpiredAt().isAfter(now) || !token.getExpiredAt().isAfter(now)) {
                revoke(session, now, "EXPIRED");
                return null;
            }
            if (token.getUsedAt() != null) {
                revoke(session, now, "REUSE_DETECTED");
                return null;
            }
            if (Boolean.TRUE.equals(token.getRevoked())) {
                revoke(session, now, "TOKEN_REVOKED");
                return null;
            }
            Account account = session.getAccount();
            entityManager.refresh(account);
            if (!allowed(account)) {
                revoke(session, now, "ACCOUNT_DISABLED_OR_LOCKED");
                return null;
            }
            if (!Objects.equals(token.getAccount().getId(), account.getId())) {
                revoke(session, now, "ACCOUNT_MISMATCH");
                return null;
            }
            token.setUsedAt(now);
            token.setRevoked(true);
            token.setRevokedAt(now);
            token.setRevokeReason("ROTATED");
            return mint(account, session, now, ipAddress, userAgent);
        });
        // The revocation transaction has COMMITTED before the 401 is raised.
        if (result == null) throw new InvalidRefreshTokenException();
        return result;
    }

    public void logout(String rawToken) {
        if (!wellFormed(rawToken)) return;
        String hash = hash(rawToken);
        transaction.executeWithoutResult(status -> {
            UUID sessionId = tokens.findSessionIdByHash(hash).orElse(null);
            if (sessionId == null) return;
            sessions.findLocked(sessionId).ifPresent(session -> {
                if (session.getRevokedAt() == null) revoke(session, now(), "LOGOUT");
            });
        });
    }

    // Call via this injected Spring bean from a transactional password-reset service.
    // Joins the caller's transaction, unlike the request-specific rotation above.
    @Transactional
    public void revokeAll(UUID accountId, String reason) {
        OffsetDateTime now = now();
        String safeReason = truncate(reason == null ? "REVOKED_ALL" : reason, 500);
        // Acquire family-row locks first; preserve the same lock order as refresh.
        sessions.revokeAll(accountId, now, safeReason);
        tokens.revokeAccountTokens(accountId, now, safeReason);
    }

    private LoginResponse mint(Account account, RefreshSession session, OffsetDateTime now,
                               String ipAddress, String userAgent) {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        String raw = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        RefreshToken token = new RefreshToken();
        token.setAccount(account);
        token.setSession(session);
        token.setToken(hash(raw));
        token.setIssuedAt(now);
        token.setExpiredAt(session.getExpiredAt());
        token.setRevoked(false);
        token.setIpAddress(truncate(ipAddress, 100));
        token.setUserAgent(truncate(userAgent, 1000));
        tokens.save(token);
        return LoginResponse.builder()
                .accessToken(jwtService.generateToken(account))
                .tokenType("Bearer")
                .expiresIn(jwtService.getLoginAccessTokenExpirationSeconds())
                .passwordMustChange(account.getPasswordMustChanged())
                .refreshToken(raw)
                .refreshExpiresIn(Math.max(0L, Duration.between(now, session.getExpiredAt()).getSeconds()))
                .build();
    }

    private void revoke(RefreshSession session, OffsetDateTime now, String reason) {
        session.setRevokedAt(now);
        session.setRevokeReason(reason);
        // flushAutomatically writes the session change before updating its tokens.
        tokens.revokeSessionTokens(session.getId(), now, reason);
    }

    private static boolean allowed(Account account) {
        // Preserve the old login's treatment of null Boolean values.
        return account != null && !Boolean.FALSE.equals(account.getActive())
                && !Boolean.TRUE.equals(account.getLocked());
    }

    private static OffsetDateTime now() {
        return OffsetDateTime.now(ZoneOffset.UTC);
    }

    private static String truncate(String value, int max) {
        if (value == null) return null;
        if (value.length() <= max) return value;
        int end = max;
        if (Character.isHighSurrogate(value.charAt(end - 1))) end--;
        return value.substring(0, end);
    }

    private static boolean wellFormed(String token) {
        return token != null && token.matches("^[A-Za-z0-9_-]{43}$");
    }

    private static String hash(String token) {
        try {
            return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256")
                    .digest(token.getBytes(StandardCharsets.US_ASCII)));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 unavailable", e);
        }
    }
}
