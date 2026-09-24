package com.owlet.api.config;

import com.owlet.api.security.jwt.JwtAccessDeniedHandler;
import com.owlet.api.security.jwt.JwtAuthenticationEntryPoint;
import com.owlet.api.security.jwt.JwtAuthenticationFilter;
import com.owlet.api.security.jwt.RefreshEndpointPaths;
import com.owlet.api.security.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final PublicEndpointRegistry publicEndpointRegistry;
    private final RefreshEndpointPaths refreshEndpointPaths;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            DaoAuthenticationProvider authenticationProvider
    ) throws Exception {

        String[] dynamicPublicEndpoints =
                publicEndpointRegistry.getPublicEndpoints();

        http
                .csrf(AbstractHttpConfigurer::disable)

                .cors(Customizer.withDefaults())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(
                                jwtAuthenticationEntryPoint
                        )
                        .accessDeniedHandler(
                                jwtAccessDeniedHandler
                        )
                )

                .authenticationProvider(authenticationProvider)

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .authorizeHttpRequests(auth -> {

                    // درخواست‌های preflight
                    auth.requestMatchers(
                            HttpMethod.OPTIONS,
                            "/**"
                    ).permitAll();

                    // این مسیرها حتی در صورت حضور در رجیستری عمومی،
                    // نیازمند احراز هویت هستند.
                    auth.requestMatchers(
                            "/api/auth/changePassword",
                            "/api/auth/currentUserRoles"
                    ).authenticated();

                    // مسیرهای عمومی قبلی
                    auth.requestMatchers(
                            "/",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**"
                    ).permitAll();

                    // ورود
                    auth.requestMatchers(
                            HttpMethod.POST,
                            "/api/auth/login"
                    ).permitAll();

                    // تمدید و ابطال نشست؛ اعتبارسنجی Refresh Token
                    // در RefreshTokenService انجام می‌شود.
                    auth.requestMatchers(
                            HttpMethod.POST,
                            refreshEndpointPaths.refreshPath(),
                            refreshEndpointPaths.revokePath()
                    ).permitAll();

                    // حفظ رفتار قبلی ایجاد حساب
                    auth.requestMatchers(
                            HttpMethod.POST,
                            "/accounts"
                    ).permitAll();

                    // حفظ رجیستری مسیرهای دارای @PublicEndpoint
                    if (dynamicPublicEndpoints != null
                            && dynamicPublicEndpoints.length > 0) {

                        auth.requestMatchers(
                                dynamicPublicEndpoints
                        ).permitAll();
                    }

                    // سایر مسیرها
                    auth.anyRequest().authenticated();
                });

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
            CustomUserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }
}