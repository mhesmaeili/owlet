package com.owlet.api.service.idm.impl;

import com.owlet.api.domain.idm.Role;
import com.owlet.api.dto.idm.RoleCreateRequest;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.mapper.idm.RoleMapper;
import com.owlet.api.repository.idm.RoleRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.idm.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class RoleImpl extends CrudServiceImpl<
        Role,
        UUID,
        RoleDto,
        RoleCreateRequest,
        RoleCreateRequest,
        RoleRepository,
        RoleMapper>
        implements RoleService {


    public RoleImpl(
            RoleRepository repository,
            RoleMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<Role> entityClass() {
        return Role.class;
    }


    @Override
    public RoleDto getByRoleCode(String code) {
        return mapper.toDto(repository.findByCodeAndActiveTrue(code));
    }
}