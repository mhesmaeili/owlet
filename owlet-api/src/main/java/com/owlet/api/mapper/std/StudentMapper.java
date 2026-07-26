package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.Student;
import com.owlet.api.dto.std.StudentCreateRequest;
import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface StudentMapper extends CrudMapper<
        Student,
        StudentDto,
        StudentCreateRequest,
        StudentCreateRequest> {

    @Mapping(target = "gender",
            source = "gender",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")
    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Override
    Student toEntity(StudentCreateRequest studentCreateRequest);

    @Mapping(target = "gender",
            source = "gender",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")
    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Override
    void update(StudentCreateRequest studentCreateRequest, @MappingTarget Student student);
}