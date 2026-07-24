package com.owlet.api.service.idm;

import com.owlet.api.dto.idm.AccountRoleCreateRequest;
import com.owlet.api.dto.idm.AccountRoleDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface AccountRoleService extends CrudService<
        UUID,
        AccountRoleDto,
        AccountRoleCreateRequest,
        AccountRoleCreateRequest> {

}