package com.owlet.api.service.idm.impl;

import com.owlet.api.domain.idm.AccountRole;
import com.owlet.api.dto.idm.AccountRoleCreateRequest;
import com.owlet.api.dto.idm.AccountRoleDto;
import com.owlet.api.mapper.idm.AccountRoleMapper;
import com.owlet.api.repository.idm.AccountRoleRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.idm.AccountRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class AccountRoleImpl extends CrudServiceImpl<
        AccountRole,
        UUID,
        AccountRoleDto,
        AccountRoleCreateRequest,
        AccountRoleCreateRequest,
        AccountRoleRepository,
        AccountRoleMapper>
        implements AccountRoleService {


    public AccountRoleImpl(
            AccountRoleRepository repository,
            AccountRoleMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<AccountRole> entityClass() {
        return AccountRole.class;
    }


}