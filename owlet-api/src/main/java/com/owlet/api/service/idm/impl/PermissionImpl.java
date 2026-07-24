package com.owlet.api.service.idm.impl;

import com.owlet.api.domain.idm.Permission;
import com.owlet.api.dto.idm.PermissionCreateRequest;
import com.owlet.api.dto.idm.PermissionDto;
import com.owlet.api.mapper.idm.PermissionMapper;
import com.owlet.api.repository.idm.PermissionRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.idm.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class PermissionImpl extends CrudServiceImpl<
        Permission,
        UUID,
        PermissionDto,
        PermissionCreateRequest,
        PermissionCreateRequest,
        PermissionRepository,
        PermissionMapper>
        implements PermissionService {


    public PermissionImpl(
            PermissionRepository repository,
            PermissionMapper mapper,
            AuditableService auditableService,
            PermissionRepository PermissionRepository) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<Permission> entityClass() {
        return Permission.class;
    }


}