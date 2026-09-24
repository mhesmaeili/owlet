package com.owlet.api.security.jwt;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties properties;

    private SecretKey key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecret()));
    }

    public long getLoginAccessTokenExpirationSeconds() {
        return seconds(properties.getLoginAccessTokenExpiration());
    }

    private long seconds(long minutes) {
        if (minutes <= 0) throw new IllegalStateException("JWT expiration must be positive");
        return Math.multiplyExact(minutes, 60L);
    }

    public String generateToken(Account account) {
        Instant now = Instant.now();
        return Jwts.builder()
                // Preserve the original subject and username semantics.
                .subject(account.getId().toString())
                .claim("username", account.getMobile())
                .claim(SecurityConstants.CLAIM_USERNAME, account.getMobile())
                .claim(SecurityConstants.CLAIM_ACCOUNT_ID, account.getId().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(getLoginAccessTokenExpirationSeconds())))
                .signWith(SignatureAlgorithm.HS256, key())
                .compact();
    }

    public String generateToken(UUID accountId, String username, Collection<String> roles) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .claim(SecurityConstants.CLAIM_ACCOUNT_ID, accountId)
                .claim(SecurityConstants.CLAIM_USERNAME, username)
                .claim(SecurityConstants.CLAIM_ROLES, List.copyOf(roles))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(seconds(properties.getAccessTokenExpiration()))))
                .signWith(key())
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(key()).build()
                .parseSignedClaims(token).getPayload();
    }

    public String extractUsername(String token) {
        Claims claims = extractAllClaims(token);
        Object username = claims.get("username");
        if (username == null) username = claims.get(SecurityConstants.CLAIM_USERNAME);
        if (username == null) throw new IllegalArgumentException("Missing username claim");
        return username.toString();
    }

    public UUID extractAccountId(String token) {
        Claims claims = extractAllClaims(token);
        Object accountId = claims.get(SecurityConstants.CLAIM_ACCOUNT_ID);
        // Old generateToken(Account) tokens carried the ID only in sub.
        return UUID.fromString(accountId != null ? accountId.toString() : claims.getSubject());
    }

    public boolean isExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration == null || !expiration.after(new Date());
    }

    public boolean isValid(String token) {
        try {
            return !isExpired(token);
        } catch (Exception ex) {
            return false;
        }
    }
}
