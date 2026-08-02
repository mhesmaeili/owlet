package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.StudentParent;
import com.owlet.api.dto.std.StudentParentCreateRequest;
import com.owlet.api.dto.std.StudentParentDto;
import com.owlet.api.mapper.std.StudentParentMapper;
import com.owlet.api.repository.std.StudentParentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentParentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StudentParentImpl extends CrudServiceImpl<
        StudentParent,
        UUID,
        StudentParentDto,
        StudentParentCreateRequest,
        StudentParentCreateRequest,
        StudentParentRepository,
        StudentParentMapper>
        implements StudentParentService {

    public StudentParentImpl(
            StudentParentRepository repository,
            StudentParentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<StudentParent> entityClass() {
        return StudentParent.class;
    }
}