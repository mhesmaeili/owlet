package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.SessionAssessment;
import com.owlet.api.dto.ses.SessionAssessmentCreateRequest;
import com.owlet.api.dto.ses.SessionAssessmentDto;
import com.owlet.api.mapper.ses.SessionAssessmentMapper;
import com.owlet.api.repository.ses.SessionAssessmentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.SessionAssessmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class SessionAssessmentImpl extends CrudServiceImpl<
        SessionAssessment,
        UUID,
        SessionAssessmentDto,
        SessionAssessmentCreateRequest,
        SessionAssessmentCreateRequest,
        SessionAssessmentRepository,
        SessionAssessmentMapper>
        implements SessionAssessmentService {

    public SessionAssessmentImpl(
            SessionAssessmentRepository repository,
            SessionAssessmentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<SessionAssessment> entityClass() {
        return SessionAssessment.class;
    }


}