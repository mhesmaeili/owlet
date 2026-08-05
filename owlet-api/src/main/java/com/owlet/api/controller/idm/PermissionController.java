package com.owlet.api.controller.idm;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.idm.PermissionCreateRequest;
import com.owlet.api.dto.idm.PermissionDto;
import com.owlet.api.security.permission.annotation.CrudPermission;
import com.owlet.api.security.permission.enums.Module;
import com.owlet.api.service.idm.PermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "PermissionController")
@RestController
@RequestMapping("/api/idm/permission")
@CrudPermission(Module.ACCOUNT)
public class PermissionController extends CrudController<
        UUID,
        PermissionDto,
        PermissionCreateRequest,
        PermissionCreateRequest> {

    public PermissionController(PermissionService service) {
        super(service);
    }

}