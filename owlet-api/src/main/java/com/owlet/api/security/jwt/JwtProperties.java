package com.owlet.api.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {
    /** Base64 Secret; keep the existing secret. */
    private String secret;

    /** Minutes; existing overload with accountId / username / roles. */
    private long accessTokenExpiration = 60;

    /** Minutes; preserves the old five-day lifetime of generateToken(Account). */
    private long loginAccessTokenExpiration = 5L * 24 * 60;

    /** Absolute session lifetime, in days; rotation does not extend it. */
    private long refreshTokenExpirationDays = 30;
}
