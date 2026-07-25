package com.owlet.api.service.idm.impl;

import com.owlet.api.domain.idm.RolePermission;
import com.owlet.api.dto.idm.RolePermissionCreateRequest;
import com.owlet.api.dto.idm.RolePermissionDto;
import com.owlet.api.mapper.idm.RolePermissionMapper;
import com.owlet.api.repository.idm.RolePermissionRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.idm.RolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class RolePermissionImpl extends CrudServiceImpl<
        RolePermission,
        UUID,
        RolePermissionDto,
        RolePermissionCreateRequest,
        RolePermissionCreateRequest,
        RolePermissionRepository,
        RolePermissionMapper>
        implements RolePermissionService {


    public RolePermissionImpl(
            RolePermissionRepository repository,
            RolePermissionMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<RolePermission> entityClass() {
        return RolePermission.class;
    }


}