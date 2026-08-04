package com.owlet.api.security.permission.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {


    private static final String SUPER_ADMIN_ROLE = "Role_Super_Administrator";

    public boolean hasPermission(
            Authentication authentication,
            String module,
            String action
    ) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // ساخت فرمت پرمیشن‌ها بر اساس استاندارد (مثال: ACCOUNT:CREATE)
        String exactAuthority = module + ":" + action;
        String wildcardAuthority = module + ":*";

        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(a -> {
                    String userAuth = a.getAuthority();

                    // مقایسه بدون حساسیت به حروف کوچک و بزرگ انجام می‌شود
                    return userAuth.equalsIgnoreCase(SUPER_ADMIN_ROLE) ||
                            userAuth.equalsIgnoreCase(exactAuthority) ||
                            userAuth.equalsIgnoreCase(wildcardAuthority);
                });
    }
}