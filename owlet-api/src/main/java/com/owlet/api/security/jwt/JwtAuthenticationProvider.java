package com.owlet.api.security.jwt;

import com.owlet.api.security.CurrentUser;
import com.owlet.api.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public UsernamePasswordAuthenticationToken authenticate(String token) {

        if (!jwtService.isValid(token)) {
            return null;
        }

        try {

            String username = jwtService.extractUsername(token);

            CurrentUser currentUser =
                    (CurrentUser) userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(
                    currentUser,
                    null,
                    currentUser.getAuthorities());

        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new AuthenticationServiceException("JWT authentication failed", ex);
        }
    }
}