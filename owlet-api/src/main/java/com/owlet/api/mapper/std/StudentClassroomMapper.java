package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.StudentClassroom;
import com.owlet.api.dto.std.StudentClassroomCreateRequest;
import com.owlet.api.dto.std.StudentClassroomDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(config = BaseMapperConfig.class)
public interface StudentClassroomMapper extends CrudMapper<
        StudentClassroom,
        StudentClassroomDto,
        StudentClassroomCreateRequest,
        StudentClassroomCreateRequest> {

    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Override
    StudentClassroom toEntity(StudentClassroomCreateRequest studentClassroomCreateRequest);

    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Override
    void update(StudentClassroomCreateRequest studentClassroomCreateRequest, @MappingTarget StudentClassroom studentClassroom);
}