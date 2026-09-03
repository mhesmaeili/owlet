package com.owlet.api.service.std.impl;

import com.owlet.api.constant.RoleConst;
import com.owlet.api.domain.std.Parent;
import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.idm.AccountRoleCreateRequest;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.dto.std.ParentCreateRequest;
import com.owlet.api.dto.std.ParentDto;
import com.owlet.api.mapper.std.ParentMapper;
import com.owlet.api.repository.std.ParentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.service.idm.AccountRoleService;
import com.owlet.api.service.idm.AccountService;
import com.owlet.api.service.idm.RoleService;
import com.owlet.api.service.std.ParentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
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
            AuditableService auditableService, AccountService accountService, AccountRoleService accountRoleService, RoleService roleService) {

        super(repository, mapper, auditableService);
        this.accountService = accountService;
        this.accountRoleService = accountRoleService;
        this.roleService = roleService;
    }

    private final AccountService accountService;
    private final AccountRoleService accountRoleService;
    private final RoleService roleService;


    @Override
    protected Class<Parent> entityClass() {
        return Parent.class;
    }

    @Override
    protected void beforeCreate(ParentCreateRequest dto) {
        if(dto.getAccount() == null) {

            AccountCreateRequest accountCreateRequest = new AccountCreateRequest();
            accountCreateRequest.setFirstName(dto.getFirstName());
            accountCreateRequest.setLastName(dto.getLastName());
            accountCreateRequest.setUsername(dto.getMobile());
            accountCreateRequest.setMobile(dto.getMobile());
            accountCreateRequest.setGender(dto.getGender());
            accountCreateRequest.setPassword(makePass());
            accountCreateRequest.setPasswordPlain(accountCreateRequest.getPassword());
            accountCreateRequest.setPasswordMustChanged(true);

            AccountDto accountDto = accountService.create(accountCreateRequest);

            AccountRoleCreateRequest accountRoleCreateRequest=new AccountRoleCreateRequest();
            accountRoleCreateRequest.setAccount(new EntityIdDto(accountDto.getId()));
            RoleDto roleDto = roleService.getByRoleCode(RoleConst.ROLE_PARENT);
            accountRoleCreateRequest.setRole(new EntityIdDto(roleDto.getId()));
            accountRoleService.create(accountRoleCreateRequest);

            dto.setAccount(new EntityIdDto(accountDto.getId()));
        }
    }

    private String makePass(){
        SecureRandom secureRandom = new SecureRandom();
        return String.valueOf(10000 + secureRandom.nextInt(90000));
    }
}