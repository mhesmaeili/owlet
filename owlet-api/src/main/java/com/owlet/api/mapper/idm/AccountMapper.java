package com.owlet.api.mapper.idm;

import com.owlet.api.domain.idm.Account;
import com.owlet.api.dto.idm.AccountCreateRequest;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.idm.AccountUpdateRequest;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class)
public interface AccountMapper extends CrudMapper<
        Account,
        AccountDto,
        AccountCreateRequest,
        AccountUpdateRequest> {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "accountRoles", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "failedLoginCount", ignore = true)
    @Mapping(target = "lastLoginAt", ignore = true)
    @Mapping(target = "passwordChangedAt", ignore = true)
    Account toEntity(AccountCreateRequest request);

}