package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.domain.org.TeacherClassroom;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.TeacherClassroomCreateRequest;
import com.owlet.api.dto.org.TeacherClassroomDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface TeacherClassroomMapper extends CrudMapper<
        TeacherClassroom,
        TeacherClassroomDto,
        TeacherClassroomCreateRequest,
        TeacherClassroomCreateRequest> {

    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")

    @Override
    TeacherClassroom toEntity(TeacherClassroomCreateRequest teacherClassroomCreateRequest);

    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Override
    void update(TeacherClassroomCreateRequest teacherClassroomCreateRequest, @MappingTarget TeacherClassroom teacherClassroom);
}