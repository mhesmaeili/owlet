package com.owlet.api.mapper.idm;

import com.owlet.api.domain.idm.RolePermission;
import com.owlet.api.dto.idm.RolePermissionCreateRequest;
import com.owlet.api.dto.idm.RolePermissionDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface RolePermissionMapper extends CrudMapper<
        RolePermission,
        RolePermissionDto,
        RolePermissionCreateRequest,
        RolePermissionCreateRequest> {

    @Mapping(target = "role",
            source = "role",
            qualifiedByName = "toReference")
    @Mapping(target = "permission",
            source = "permission",
            qualifiedByName = "toReference")
    @Override
    RolePermission toEntity(RolePermissionCreateRequest rolePermissionCreateRequest);

    @Mapping(target = "role",
            source = "role",
            qualifiedByName = "toReference")
    @Mapping(target = "permission",
            source = "permission",
            qualifiedByName = "toReference")
    @Override
    void update(RolePermissionCreateRequest rolePermissionCreateRequest,@MappingTarget RolePermission rolePermission);
}