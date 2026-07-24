package com.owlet.api.controller.idm;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.idm.RoleCreateRequest;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.service.idm.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "RoleController")
@RestController
@RequestMapping("/api/idm/role")
public class RoleController extends CrudController<
        UUID,
        RoleDto,
        RoleCreateRequest,
        RoleCreateRequest> {

    public RoleController(RoleService service) {
        super(service);
    }

}