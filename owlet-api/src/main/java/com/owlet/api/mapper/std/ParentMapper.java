package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.Parent;
import com.owlet.api.dto.std.ParentCreateRequest;
import com.owlet.api.dto.std.ParentDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface ParentMapper extends CrudMapper<
        Parent,
        ParentDto,
        ParentCreateRequest,
        ParentCreateRequest> {

    @Mapping(target = "gender",
            source = "gender",
            qualifiedByName = "toReference")
    @Mapping(target = "occupation",
            source = "occupation",
            qualifiedByName = "toReference")
    @Mapping(target = "education",
            source = "education",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Override
    Parent toEntity(ParentCreateRequest parentCreateRequest);

    @Mapping(target = "gender",
            source = "gender",
            qualifiedByName = "toReference")
    @Mapping(target = "occupation",
            source = "occupation",
            qualifiedByName = "toReference")
    @Mapping(target = "education",
            source = "education",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Override
    void update(ParentCreateRequest parentCreateRequest, @MappingTarget Parent parent);
}