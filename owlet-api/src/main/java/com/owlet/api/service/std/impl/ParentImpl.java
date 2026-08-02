package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.Parent;
import com.owlet.api.dto.std.ParentCreateRequest;
import com.owlet.api.dto.std.ParentDto;
import com.owlet.api.mapper.std.ParentMapper;
import com.owlet.api.repository.std.ParentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.ParentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ParentImpl extends CrudServiceImpl<
        Parent,
        UUID,
        ParentDto,
        ParentCreateRequest,
        ParentCreateRequest,
        ParentRepository,
        ParentMapper>
        implements ParentService {

    public ParentImpl(
            ParentRepository repository,
            ParentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<Parent> entityClass() {
        return Parent.class;
    }
}