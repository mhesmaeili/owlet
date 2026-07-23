package com.owlet.api.security.jwt;

import com.owlet.api.security.SecurityConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider authenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader(SecurityConstants.HEADER_NAME);

        if (StringUtils.hasText(header)
                && header.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            String token =
                    header.substring(SecurityConstants.TOKEN_PREFIX.length());

            try {

                Authentication authentication =
                        authenticationProvider.authenticate(token);

                if (authentication != null) {
                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }

            } catch (AuthenticationException ex) {

                SecurityContextHolder.clearContext();

            }
        }

        filterChain.doFilter(request, response);
    }
}