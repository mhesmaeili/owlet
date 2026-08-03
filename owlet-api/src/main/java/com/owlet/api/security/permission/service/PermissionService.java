package com.owlet.api.security.permission.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {


    public boolean hasPermission(
            Authentication authentication,
            String module,
            String action
    ) {

        if (authentication == null) {
            return false;
        }


        String authority =
                module +
                        ":" +
                        action;


        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(
                        a -> a.getAuthority()
                                .equals(authority)
                );
    }
}
