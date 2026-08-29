package com.owlet.api.mapper.ses;


import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface TrainingCourseMapper extends CrudMapper<
        TrainingCourse,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest> {

    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "product",
            source = "product",
            qualifiedByName = "toReference")
    @Mapping(target = "trainingStatus",
            source = "trainingStatus",
            qualifiedByName = "toReference")
    @Override
    void update(TrainingCourseCreateRequest trainingCourseCreateRequest, @MappingTarget TrainingCourse trainingCourse);

    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "product",
            source = "product",
            qualifiedByName = "toReference")
    @Mapping(target = "trainingStatus",
            source = "trainingStatus",
            qualifiedByName = "toReference")
    @Override
    TrainingCourse toEntity(TrainingCourseCreateRequest trainingCourseCreateRequest);


}