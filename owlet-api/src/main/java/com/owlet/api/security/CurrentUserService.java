package com.owlet.api.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CurrentUserService {

    public CurrentUser getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            return null;

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CurrentUser currentUser))
            return null;

        return currentUser;
    }

    public UUID getCurrentUserId() {

        CurrentUser user = getCurrentUser();

        return user == null ? null : user.getAccountId();
    }

    public String getUsername() {

        CurrentUser user = getCurrentUser();

        return user == null ? null : user.getUsername();
    }

}
