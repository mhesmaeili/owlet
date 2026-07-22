package com.owlet.api.security.jwt;

import com.owlet.api.security.CurrentUser;
import com.owlet.api.security.SecurityConstants;
import com.owlet.api.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader(SecurityConstants.HEADER_NAME);

        if (!StringUtils.hasText(header) ||
                !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {

            filterChain.doFilter(request, response);

            return;
        }

        String token =
                header.substring(SecurityConstants.TOKEN_PREFIX.length());

        if (!jwtService.isValid(token)) {

            filterChain.doFilter(request, response);

            return;
        }

        String username =
                jwtService.extractUsername(token);

        CurrentUser currentUser =
                (CurrentUser) userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        currentUser,
                        null,
                        currentUser.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        filterChain.doFilter(request, response);

    }

}