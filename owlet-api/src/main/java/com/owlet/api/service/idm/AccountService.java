package com.owlet.api.service.idm;

import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

public interface AccountService {

    AccountDto create(AccountCreateRequest request);

    AccountDto update(UUID id, AccountCreateRequest request);

    AccountDto findById(UUID id);

    Page<AccountDto> findAll(Pageable pageable);

    void delete(UUID id);

}