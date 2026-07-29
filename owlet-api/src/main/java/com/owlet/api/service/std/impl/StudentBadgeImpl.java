package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.StudentBadge;
import com.owlet.api.dto.std.StudentBadgeCreateRequest;
import com.owlet.api.dto.std.StudentBadgeDto;
import com.owlet.api.mapper.std.StudentBadgeMapper;
import com.owlet.api.repository.std.StudentBadgeRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentBadgeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StudentBadgeImpl extends CrudServiceImpl<
        StudentBadge,
        UUID,
        StudentBadgeDto,
        StudentBadgeCreateRequest,
        StudentBadgeCreateRequest,
        StudentBadgeRepository,
        StudentBadgeMapper>
        implements StudentBadgeService {

    public StudentBadgeImpl(
            StudentBadgeRepository repository,
            StudentBadgeMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<StudentBadge> entityClass() {
        return StudentBadge.class;
    }
}