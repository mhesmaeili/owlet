package com.owlet.api.mapper.ses;


import com.owlet.api.domain.ses.TeacherObservation;
import com.owlet.api.dto.ses.TeacherObservationCreateRequest;
import com.owlet.api.dto.ses.TeacherObservationDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface TeacherObservationMapper extends CrudMapper<
        TeacherObservation,
        TeacherObservationDto,
        TeacherObservationCreateRequest,
        TeacherObservationCreateRequest> {

    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "sessionStudent",
            source = "sessionStudent",
            qualifiedByName = "toReference")
    @Override
    void update(TeacherObservationCreateRequest TeacherObservationCreateRequest, @MappingTarget TeacherObservation TeacherObservation);

    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "sessionStudent",
            source = "sessionStudent",
            qualifiedByName = "toReference")
    @Override
    TeacherObservation toEntity(TeacherObservationCreateRequest TeacherObservationCreateRequest);


}