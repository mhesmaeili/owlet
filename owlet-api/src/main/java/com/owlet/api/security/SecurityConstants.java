package com.owlet.api.security;

public class SecurityConstants {
    private SecurityConstants() {
    }

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_NAME = "Authorization";

    public static final String CLAIM_ACCOUNT_ID = "accountId";

    public static final String CLAIM_USERNAME = "username";

    public static final String CLAIM_ROLES = "roles";
}
