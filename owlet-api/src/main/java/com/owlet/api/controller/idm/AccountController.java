package com.owlet.api.controller.idm;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.idm.AccountUpdateRequest;
import com.owlet.api.security.permission.annotation.CrudPermission;
import com.owlet.api.security.permission.enums.Module;
import com.owlet.api.service.idm.AccountService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "AccountController")
@RestController
@RequestMapping("/api/idm/account")
@CrudPermission(Module.ACCOUNT)
public class AccountController extends CrudController<
        UUID,
        AccountDto,
        AccountCreateRequest,
        AccountUpdateRequest> {

    public AccountController(AccountService service) {
        super(service);
    }

    @Override
    public ApiResponse<AccountDto> create(AccountCreateRequest dto) {
        dto.setPasswordMustChanged(false);
        return super.create(dto);
    }
}