package com.owlet.api.service.idm;


import com.owlet.api.dto.idm.PermissionCreateRequest;
import com.owlet.api.dto.idm.PermissionDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface PermissionService extends CrudService<
        UUID,
        PermissionDto,
        PermissionCreateRequest,
        PermissionCreateRequest> {
}