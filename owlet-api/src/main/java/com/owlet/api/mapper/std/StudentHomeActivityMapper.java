package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.StudentHomeActivity;
import com.owlet.api.dto.std.StudentHomeActivityCreateRequest;
import com.owlet.api.dto.std.StudentHomeActivityDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface StudentHomeActivityMapper extends CrudMapper<
        StudentHomeActivity,
        StudentHomeActivityDto,
        StudentHomeActivityCreateRequest,
        StudentHomeActivityCreateRequest> {

    @Mapping(target = "sessionStudent",
            source = "sessionStudent",
            qualifiedByName = "toReference")
    @Override
    StudentHomeActivity toEntity(StudentHomeActivityCreateRequest studentHomeActivityCreateRequest);

    @Mapping(target = "sessionStudent",
            source = "sessionStudent",
            qualifiedByName = "toReference")
    @Override
    void update(StudentHomeActivityCreateRequest studentHomeActivityCreateRequest, @MappingTarget StudentHomeActivity studentHomeActivity);
}