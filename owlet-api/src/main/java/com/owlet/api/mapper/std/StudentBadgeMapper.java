package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.StudentBadge;
import com.owlet.api.dto.std.StudentBadgeCreateRequest;
import com.owlet.api.dto.std.StudentBadgeDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface StudentBadgeMapper extends CrudMapper<
        StudentBadge,
        StudentBadgeDto,
        StudentBadgeCreateRequest,
        StudentBadgeCreateRequest> {

    @Mapping(target = "sessionStudent",
            source = "sessionStudent",
            qualifiedByName = "toReference")
    @Mapping(target = "badgeType",
            source = "badgeType",
            qualifiedByName = "toReference")
    @Override
    StudentBadge toEntity(StudentBadgeCreateRequest studentBadgeCreateRequest);

    @Mapping(target = "sessionStudent",
            source = "sessionStudent",
            qualifiedByName = "toReference")
    @Mapping(target = "badgeType",
            source = "badgeType",
            qualifiedByName = "toReference")
    @Override
    void update(StudentBadgeCreateRequest studentBadgeCreateRequest, @MappingTarget StudentBadge studentBadge);
}