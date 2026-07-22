package com.owlet.api.service.idm.impl;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.idm.AccountRole;
import com.owlet.api.domain.idm.Role;
import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.mapper.idm.AccountMapper;
import com.owlet.api.repository.idm.AccountRepository;
import com.owlet.api.repository.idm.AccountRoleRepository;
import com.owlet.api.repository.idm.RoleRepository;
import com.owlet.api.service.idm.AccountService;
import com.owlet.common.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final RoleRepository roleRepository;

    private final AccountRoleRepository accountRoleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;

    @Override
    public AccountDto create(AccountCreateRequest request) {

        validateCreateRequest(request);

        Account account = accountMapper.toEntity(request);

        account.setId(UUID.randomUUID());

        account.setPasswordHash(
                passwordEncoder.encode(request.getPassword()));

        account.setActive(true);

        account.setLocked(false);

        account.setFailedLoginCount(0);

        account.setLastLoginAt(null);

        account.setPasswordChangedAt(OffsetDateTime.now());

        Account savedAccount =
                accountRepository.save(account);

        assignRoles(savedAccount, request.getRoleIds());

        return accountMapper.toDto(savedAccount);

    }

    // ----------------------------------------------------------------------

    private void assignRoles(
            Account account,
            List<UUID> roleIds) {

        if (roleIds == null || roleIds.isEmpty()) {
            return;
        }

        for (UUID roleId : roleIds) {

            Role role = roleRepository.findById(roleId)

                    .orElseThrow(() ->
                            new EntityNotFoundException(
                                    "Role not found : " + roleId));

            AccountRole accountRole = new AccountRole();

            accountRole.setId(UUID.randomUUID());

            accountRole.setAccount(account);

            accountRole.setRole(role);

            accountRole.setAssignedAt(OffsetDateTime.now());

            accountRole.setActive(true);

            accountRoleRepository.save(accountRole);

        }

    }

    // ----------------------------------------------------------------------

    private void validateCreateRequest(
            AccountCreateRequest request) {

        if (accountRepository.existsByUsername(request.getUsername())) {

            throw new BusinessException(
                    "Username already exists.");

        }

        if (request.getEmail() != null
                && accountRepository.existsByEmail(request.getEmail())) {

            throw new BusinessException(
                    "Email already exists.");

        }

        if (request.getMobile() != null
                && accountRepository.existsByMobile(request.getMobile())) {

            throw new BusinessException(
                    "Mobile already exists.");

        }

    }

    @Override
    public AccountDto update(
            UUID id,
            AccountCreateRequest request) {

        Account account = accountRepository
                .findByIdAndDeletedFalse(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Account not found : " + id));


        if (request.getEmail() != null
                && !request.getEmail().equals(account.getEmail())
                && accountRepository.existsByEmail(request.getEmail())) {

            throw new IllegalArgumentException(
                    "Email already exists.");

        }


        if (request.getMobile() != null
                && !request.getMobile().equals(account.getMobile())
                && accountRepository.existsByMobile(request.getMobile())) {

            throw new IllegalArgumentException(
                    "Mobile already exists.");

        }


        /*accountMapper.updateEntity(
                request,
                account
        );*/


        if (request.getPassword() != null
                && !request.getPassword().isBlank()) {

            account.setPasswordHash(
                    passwordEncoder.encode(
                            request.getPassword()));

            account.setPasswordChangedAt(
                    OffsetDateTime.now());

        }


        Account updated =
                accountRepository.save(account);


        if (request.getRoleIds() != null) {

            updateRoles(
                    updated,
                    request.getRoleIds());

        }


        return accountMapper.toDto(updated);

    }

    private void updateRoles(
            Account account,
            List<UUID> roleIds) {


        accountRoleRepository.deleteByAccountId(
                account.getId());


        for (UUID roleId : roleIds) {


            Role role =
                    roleRepository.findById(roleId)

                            .orElseThrow(() ->
                                    new EntityNotFoundException(
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

    @Override
    @Transactional(readOnly = true)
    public Page<AccountDto> findAll(
            Pageable pageable) {


        return accountRepository
                .findAllByDeletedFalse(pageable)

                .map(accountMapper::toDto);

    }

    @Override
    @Transactional(readOnly = true)
    public AccountDto findById(UUID id) {


        Account account =
                accountRepository
                        .findByIdAndDeletedFalse(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Account not found : " + id));


        return accountMapper.toDto(account);

    }

    @Override
    public void delete(UUID id) {


        Account account =
                accountRepository
                        .findByIdAndDeletedFalse(id)

                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Account not found : " + id));


        account.setDeleted(true);

        account.setDeletedAt(
                OffsetDateTime.now());


        accountRepository.save(account);

    }

}