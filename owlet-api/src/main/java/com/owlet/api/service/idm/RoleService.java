package com.owlet.api.service.idm;


import com.owlet.api.dto.idm.RoleCreateRequest;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface RoleService extends CrudService<
        UUID,
        RoleDto,
        RoleCreateRequest,
        RoleCreateRequest> {
    RoleDto getByRoleCode(String code);
}