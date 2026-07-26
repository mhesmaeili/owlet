package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.TeacherObservation;
import com.owlet.api.dto.ses.TeacherObservationCreateRequest;
import com.owlet.api.dto.ses.TeacherObservationDto;
import com.owlet.api.mapper.ses.TeacherObservationMapper;
import com.owlet.api.repository.ses.TeacherObservationRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.TeacherObservationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<TeacherObservation> entityClass() {
        return TeacherObservation.class;
    }
}