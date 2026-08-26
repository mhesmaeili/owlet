package com.owlet.api.service.ses.impl;

import com.owlet.api.constant.ReferenceType;
import com.owlet.api.domain.ses.Session;
import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.mapper.ses.SessionMapper;
import com.owlet.api.repository.ses.SessionRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.service.ref.ReferenceItemService;
import com.owlet.api.service.ses.SessionService;
import com.owlet.api.service.ses.SessionStudentService;
import com.owlet.common.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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
            AuditableService auditableService, ReferenceItemService referenceItemService, SessionStudentService sessionStudentService) {

        super(repository, mapper, auditableService);
        this.referenceItemService = referenceItemService;
        this.sessionStudentService = sessionStudentService;
    }

    private final ReferenceItemService referenceItemService;
    private final SessionStudentService sessionStudentService;


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

    @Override
    public void addByTrainingCourse(TrainingCourse entity) {
        List<ReferenceItemDto> referenceItemDtos = referenceItemService.loadByTypeCode(ReferenceType.SESSION_TYPE);

        List<SessionCreateRequest> listAdded = new ArrayList<>();
        referenceItemDtos.forEach(ref -> {
            SessionCreateRequest sessionDto = new SessionCreateRequest();
            sessionDto.setTrainingCourse(new EntityIdDto(entity.getId()));
            sessionDto.setSessionType(new EntityIdDto(ref.getId()));
            sessionDto.setTitle(ref.getTitleFa());
            sessionDto.setFinalized(false);
            sessionDto.setAttendanceSubmitted(false);

            listAdded.add(sessionDto);
        });

        List<SessionDto> sessionDtos = create(listAdded);

        sessionStudentService.addBySession(sessionDtos, entity);
    }

    @Override
    public SessionDto attendanceSubmitted(UUID id) {
        Session entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session not found"));

        if (entity.getAttendanceSubmitted() == null || !entity.getAttendanceSubmitted()) {
            entity.setAttendanceSubmitted(true);
        }

        return mapper.toDto(entity);
    }
}