package com.owlet.api.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RefreshEndpointPaths {

    private static final String BASE_PATH = "/api/auth";

    public String refreshPath() {
        return BASE_PATH + "/refresh";
    }

    public String revokePath() {
        return BASE_PATH + "/refresh/revoke";
    }

    public boolean matches(HttpServletRequest request) {
        if (!"POST".equalsIgnoreCase(request.getMethod())) {
            return false;
        }

        String path = request.getServletPath();

        return refreshPath().equals(path)
                || revokePath().equals(path);
    }
}