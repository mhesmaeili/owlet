package com.owlet.api.controller.idm;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.idm.AccountRoleCreateRequest;
import com.owlet.api.dto.idm.AccountRoleDto;
import com.owlet.api.service.idm.AccountRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "AccountRoleController")
@RestController
@RequestMapping("/api/idm/accountRole")
public class AccountRoleController extends CrudController<
        UUID,
        AccountRoleDto,
        AccountRoleCreateRequest,
        AccountRoleCreateRequest> {

    public AccountRoleController(AccountRoleService service) {
        super(service);
    }

}