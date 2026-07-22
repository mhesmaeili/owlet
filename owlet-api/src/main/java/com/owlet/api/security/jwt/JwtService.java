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

        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(properties.getSecret()));
    }


    public String generateToken(Account account) {


        return Jwts.builder()
                .subject(account.getId().toString())
                .claim("username", account.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 3600000
                        )
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        key()
                )
                .compact();

    }

    public String generateToken(
            UUID accountId,
            String username,
            Collection<String> roles
    ) {

        Instant now = Instant.now();

        Instant expire =
                now.plusSeconds(properties.getAccessTokenExpiration() * 60);

        return Jwts.builder()

                .subject(username)

                .claim(SecurityConstants.CLAIM_ACCOUNT_ID, accountId)

                .claim(SecurityConstants.CLAIM_USERNAME, username)

                .claim(SecurityConstants.CLAIM_ROLES, List.copyOf(roles))

                .issuedAt(Date.from(now))

                .expiration(Date.from(expire))

                .signWith(key())

                .compact();

    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()

                .verifyWith(key())

                .build()

                .parseSignedClaims(token)

                .getPayload();

    }

    public String extractUsername(String token) {

        return extractAllClaims(token).get("username").toString();

    }

    public UUID extractAccountId(String token) {

        return UUID.fromString(

                extractAllClaims(token)

                        .get(SecurityConstants.CLAIM_ACCOUNT_ID)

                        .toString());

    }

    public boolean isExpired(String token) {

        return extractAllClaims(token)

                .getExpiration()

                .before(new Date());

    }

    public boolean isValid(String token) {

        try {

            return !isExpired(token);

        } catch (Exception ex) {

            return false;

        }

    }

}