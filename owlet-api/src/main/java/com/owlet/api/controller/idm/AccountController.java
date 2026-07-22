package com.owlet.api.controller.idm;

import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.service.idm.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public AccountDto create(
            @Valid @RequestBody AccountCreateRequest request) {

        return accountService.create(request);
    }

    @PutMapping("/{id}")
    public AccountDto update(
            @PathVariable UUID id,
            @Valid @RequestBody AccountCreateRequest request) {

        return accountService.update(id, request);
    }

    @GetMapping("/{id}")
    public AccountDto getById(
            @PathVariable UUID id) {

        return accountService.findById(id);
    }

    @GetMapping
    public Page<AccountDto> findAll(Pageable pageable) {

        return accountService.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable UUID id) {

        accountService.delete(id);
    }

}