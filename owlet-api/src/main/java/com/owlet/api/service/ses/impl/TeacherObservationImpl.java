package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.ses.TeacherObservation;
import com.owlet.api.dto.ses.TeacherObservationCreateRequest;
import com.owlet.api.dto.ses.TeacherObservationDto;
import com.owlet.api.mapper.ses.TeacherObservationMapper;
import com.owlet.api.repository.ses.TeacherObservationRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.TeacherObservationService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TeacherObservationImpl extends CrudServiceImpl<
        TeacherObservation,
        UUID,
        TeacherObservationDto,
        TeacherObservationCreateRequest,
        TeacherObservationCreateRequest,
        TeacherObservationRepository,
        TeacherObservationMapper>
        implements TeacherObservationService {

    public TeacherObservationImpl(
            TeacherObservationRepository repository,
            TeacherObservationMapper mapper,
            AuditableService auditableService, EntityManager entityManager) {

        super(repository, mapper, auditableService);
        this.entityManager = entityManager;
    }

    private final EntityManager entityManager;


    @Override
    protected Class<TeacherObservation> entityClass() {
        return TeacherObservation.class;
    }

    @Override
    protected TeacherObservation beforeCreateSave(TeacherObservation entity, TeacherObservationCreateRequest dto) {
        entity.setTeacherAccount(entityManager.getReference(Account.class, auditableService.currentUserId()));
        return super.beforeCreateSave(entity, dto);
    }

    @Override
    public List<TeacherObservationDto> getBySessionId(UUID sessionId) {
        return toDto(repository.findBySessionStudent_Session_Id(sessionId));
    }
}