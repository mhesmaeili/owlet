package com.owlet.api.service.idm;

import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.idm.AccountUpdateRequest;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface AccountService extends CrudService<
        UUID,
        AccountDto,
        AccountCreateRequest,
        AccountUpdateRequest> {

    List<RoleDto> findActiveRolesByUsername(String username);
}