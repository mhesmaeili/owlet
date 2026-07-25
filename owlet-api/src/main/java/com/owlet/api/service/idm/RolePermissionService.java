package com.owlet.api.service.idm;


import com.owlet.api.dto.idm.RolePermissionCreateRequest;
import com.owlet.api.dto.idm.RolePermissionDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface RolePermissionService extends CrudService<
        UUID,
        RolePermissionDto,
        RolePermissionCreateRequest,
        RolePermissionCreateRequest> {
}