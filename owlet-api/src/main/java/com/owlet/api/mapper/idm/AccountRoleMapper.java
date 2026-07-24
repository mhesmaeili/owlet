package com.owlet.api.mapper.idm;

import com.owlet.api.domain.idm.AccountRole;
import com.owlet.api.dto.idm.AccountRoleCreateRequest;
import com.owlet.api.dto.idm.AccountRoleDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface AccountRoleMapper extends CrudMapper<
        AccountRole,
        AccountRoleDto,
        AccountRoleCreateRequest,
        AccountRoleCreateRequest> {

    @Mapping(target = "role",
            source = "role",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Override
    AccountRole toEntity(AccountRoleCreateRequest accountRoleCreateRequest);

    @Mapping(target = "role",
            source = "role",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Override
    void update(AccountRoleCreateRequest accountRoleCreateRequest,@MappingTarget AccountRole accountRole);
}