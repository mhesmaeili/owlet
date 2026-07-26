package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface ClassroomMapper extends CrudMapper<
        Classroom,
        ClassroomDto,
        ClassroomCreateRequest,
        ClassroomCreateRequest> {

    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")
    @Mapping(target = "grade",
            source = "grade",
            qualifiedByName = "toReference")
    @Mapping(target = "academicYears",
            source = "academicYears",
            qualifiedByName = "toReference")
    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Override
    Classroom toEntity(ClassroomCreateRequest ClassroomCreateRequest);

    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")
    @Mapping(target = "grade",
            source = "grade",
            qualifiedByName = "toReference")
    @Mapping(target = "academicYears",
            source = "academicYears",
            qualifiedByName = "toReference")
    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Override
    void update(ClassroomCreateRequest classroomCreateRequest, @MappingTarget Classroom Classroom);
}