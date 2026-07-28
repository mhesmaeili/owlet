package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.mapper.ses.SessionStudentMapper;
import com.owlet.api.repository.ses.SessionStudentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.SessionStudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class SessionStudentImpl extends CrudServiceImpl<
        SessionStudent,
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest,
        SessionStudentRepository,
        SessionStudentMapper>
        implements SessionStudentService {

    public SessionStudentImpl(
            SessionStudentRepository repository,
            SessionStudentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<SessionStudent> entityClass() {
        return SessionStudent.class;
    }
}