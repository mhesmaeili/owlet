package com.owlet.api.mapper.idm;

import com.owlet.api.domain.idm.Permission;
import com.owlet.api.dto.idm.PermissionCreateRequest;
import com.owlet.api.dto.idm.PermissionDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface PermissionMapper extends CrudMapper<
        Permission,
        PermissionDto,
        PermissionCreateRequest,
        PermissionCreateRequest> {


}