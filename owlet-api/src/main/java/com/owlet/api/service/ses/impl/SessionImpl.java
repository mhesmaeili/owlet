package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.Session;
import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.mapper.ses.SessionMapper;
import com.owlet.api.repository.ses.SessionRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.SessionService;
import com.owlet.common.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@Transactional
public class SessionImpl extends CrudServiceImpl<
        Session,
        UUID,
        SessionDto,
        SessionCreateRequest,
        SessionCreateRequest,
        SessionRepository,
        SessionMapper>
        implements SessionService {

    public SessionImpl(
            SessionRepository repository,
            SessionMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<Session> entityClass() {
        return Session.class;
    }

    @Override
    public SessionDto finalizeSession(UUID id) {
        Session entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session not found"));

        entity.setFinalized(true);
        entity.setFinalizedAt(OffsetDateTime.now());

        repository.save(entity);
        return mapper.toDto(entity);
    }
}