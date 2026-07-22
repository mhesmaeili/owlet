package com.owlet.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuditorProvider
        implements AuditorAware<UUID> {

    private final CurrentUserService currentUserService;

    @Override
    public Optional<UUID> getCurrentAuditor() {

        return Optional.ofNullable(
                currentUserService.getCurrentUserId());
    }

}
