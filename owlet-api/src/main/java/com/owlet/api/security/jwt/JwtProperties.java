package com.owlet.api.security.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "security.jwt")
public class JwtProperties {

    /**
     * Base64 Secret
     */
    private String secret;

    /**
     * Minutes
     */
    private long accessTokenExpiration = 60;

}
