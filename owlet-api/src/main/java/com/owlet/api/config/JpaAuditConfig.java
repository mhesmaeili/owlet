package com.owlet.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(
        auditorAwareRef = "auditorProvider",
        dateTimeProviderRef = "dateTimeProvider"
)
public class JpaAuditConfig {
    @Bean
    public DateTimeProvider dateTimeProvider() {

        return () -> Optional.of(
                OffsetDateTime.now()
        );
    }
}
