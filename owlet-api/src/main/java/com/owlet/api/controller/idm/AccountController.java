package com.owlet.api.controller.idm;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.idm.AccountUpdateRequest;
import com.owlet.api.security.CurrentUser;
import com.owlet.api.service.idm.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "AccountController")
@RestController
@RequestMapping("/api/idm/account")
public class AccountController extends CrudController<
        UUID,
        AccountDto,
        AccountCreateRequest,
        AccountUpdateRequest> {

    public AccountController(AccountService service) {
        super(service);
    }

    @GetMapping("/me")
    public CurrentUser me(
            @AuthenticationPrincipal CurrentUser currentUser
    ) {
        return currentUser;
    }
}