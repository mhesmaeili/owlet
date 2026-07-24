package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolCreateRequest;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.org.SchoolUpdateRequest;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface SchoolMapper extends CrudMapper<
        School,
        SchoolDto,
        SchoolCreateRequest,
        SchoolUpdateRequest> {

    @Mapping(target = "schoolType",
            source = "schoolType",
            qualifiedByName = "toReference")
    @Mapping(target = "parentSchool",
            source = "parentSchool",
            qualifiedByName = "toReference")
    @Mapping(target = "managerAccount",
            source = "managerAccount",
            qualifiedByName = "toReference")
    @Override
    School toEntity(SchoolCreateRequest schoolCreateRequest);

    @Mapping(target = "schoolType",
            source = "schoolType",
            qualifiedByName = "toReference")
    @Mapping(target = "parentSchool",
            source = "parentSchool",
            qualifiedByName = "toReference")
    @Mapping(target = "managerAccount",
            source = "managerAccount",
            qualifiedByName = "toReference")
    @Override
    void update(SchoolUpdateRequest schoolUpdateRequest,@MappingTarget School school);
}