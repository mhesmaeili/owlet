package com.owlet.api.mapper.idm;

import com.owlet.api.domain.idm.Role;
import com.owlet.api.dto.idm.RoleCreateRequest;
import com.owlet.api.dto.idm.RoleDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface RoleMapper extends CrudMapper<
        Role,
        RoleDto,
        RoleCreateRequest,
        RoleCreateRequest> {


}