package com.owlet.api.controller.idm;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.idm.RolePermissionCreateRequest;
import com.owlet.api.dto.idm.RolePermissionDto;
import com.owlet.api.security.permission.annotation.CrudPermission;
import com.owlet.api.security.permission.enums.Module;
import com.owlet.api.service.idm.RolePermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "RolePermissionController")
@RestController
@RequestMapping("/api/idm/rolePermission")
@CrudPermission(Module.ACCOUNT)
public class RolePermissionController extends CrudController<
        UUID,
        RolePermissionDto,
        RolePermissionCreateRequest,
        RolePermissionCreateRequest> {

    public RolePermissionController(RolePermissionService service) {
        super(service);
    }

}