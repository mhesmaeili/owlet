package com.owlet.api.service.idm.impl;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.idm.AccountRole;
import com.owlet.api.domain.idm.Role;
import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.idm.AccountUpdateRequest;
import com.owlet.api.mapper.idm.AccountMapper;
import com.owlet.api.repository.idm.AccountRepository;
import com.owlet.api.repository.idm.AccountRoleRepository;
import com.owlet.api.repository.idm.RoleRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.idm.AccountService;
import com.owlet.common.exception.BusinessException;
import com.owlet.common.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl extends CrudServiceImpl<
        Account,
        UUID,
        AccountDto,
        AccountCreateRequest,
        AccountUpdateRequest,
        AccountRepository,
        AccountMapper>
        implements AccountService {


    private final RoleRepository roleRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final PasswordEncoder passwordEncoder;


    public AccountServiceImpl(
            AccountRepository repository,
            AccountMapper mapper,
            AuditableService auditableService,
            RoleRepository roleRepository,
            AccountRoleRepository accountRoleRepository,
            PasswordEncoder passwordEncoder) {

        super(repository, mapper, auditableService);

        this.roleRepository = roleRepository;
        this.accountRoleRepository = accountRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected Class<Account> entityClass() {
        return Account.class;
    }


    @Override
    protected void validateCreate(AccountCreateRequest request) {

        if (repository.existsByUsername(request.getUsername())) {

            throw new BusinessException(
                    "Username already exists.");
        }


        if (request.getEmail() != null
                && repository.existsByEmail(request.getEmail())) {

            throw new BusinessException(
                    "Email already exists.");
        }


        if (request.getMobile() != null
                && repository.existsByMobile(request.getMobile())) {

            throw new BusinessException(
                    "Mobile already exists.");
        }
    }

    @Override
    protected Account beforeCreateSave(
            Account account,
            AccountCreateRequest request) {

        if (request.getPassword() != null) {

            account.setPasswordHash(
                    passwordEncoder.encode(request.getPassword())
            );
        }

        account.setActive(true);
        account.setLocked(false);
        account.setFailedLoginCount(0);

        return account;
    }

    @Override
    protected Account beforeUpdateSave(
            Account account,
            AccountUpdateRequest request) {

        if (request.getPassword() != null) {

            account.setPasswordHash(
                    passwordEncoder.encode(request.getPassword())
            );

            account.setPasswordChangedAt(
                    OffsetDateTime.now());
        }

        return account;
    }


    @Override
    protected void afterUpdate(Account account) {

        updateRoles(
                account,
                account.getAccountRoles()
                        .stream()
                        .map(x -> x.getRole().getId())
                        .toList());
    }


    private void updateRoles(
            Account account,
            List<UUID> roleIds) {


        accountRoleRepository.deleteByAccountId(
                account.getId());


        assignRoles(account, roleIds);
    }

    private void assignRoles(
            Account account,
            List<UUID> roleIds) {


        if (roleIds == null || roleIds.isEmpty()) {
            return;
        }


        for (UUID roleId : roleIds) {


            Role role =
                    roleRepository.findById(roleId)
                            .orElseThrow(() ->
                                    new NotFoundException(
                                            "Role not found : " + roleId));


            AccountRole accountRole =
                    new AccountRole();


            accountRole.setId(UUID.randomUUID());
            accountRole.setAccount(account);
            accountRole.setRole(role);
            accountRole.setAssignedAt(
                    OffsetDateTime.now());
            accountRole.setActive(true);


            accountRoleRepository.save(accountRole);
        }
    }

}