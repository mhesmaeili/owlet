package com.owlet.api.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditableService {

    private final CurrentUserService currentUserService;


    public UUID currentUserId() {

        return currentUserService.getCurrentUserId();

    }


    public OffsetDateTime now() {

        return OffsetDateTime.now();

    }

}
