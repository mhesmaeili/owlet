package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.StudentParent;
import com.owlet.api.dto.std.StudentParentCreateRequest;
import com.owlet.api.dto.std.StudentParentDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface StudentParentMapper extends CrudMapper<
        StudentParent,
        StudentParentDto,
        StudentParentCreateRequest,
        StudentParentCreateRequest> {

    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "parent",
            source = "parent",
            qualifiedByName = "toReference")
    @Mapping(target = "relationshipType",
            source = "relationshipType",
            qualifiedByName = "toReference")
    @Override
    StudentParent toEntity(StudentParentCreateRequest studentParentCreateRequest);

    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "parent",
            source = "parent",
            qualifiedByName = "toReference")
    @Mapping(target = "relationshipType",
            source = "relationshipType",
            qualifiedByName = "toReference")
    @Override
    void update(StudentParentCreateRequest studentParentCreateRequest, @MappingTarget StudentParent studentParent);
}