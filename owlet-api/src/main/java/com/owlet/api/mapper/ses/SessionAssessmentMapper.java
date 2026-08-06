package com.owlet.api.mapper.ses;


import com.owlet.api.domain.ses.SessionAssessment;
import com.owlet.api.dto.ses.SessionAssessmentCreateRequest;
import com.owlet.api.dto.ses.SessionAssessmentDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface SessionAssessmentMapper extends CrudMapper<
        SessionAssessment,
        SessionAssessmentDto,
        SessionAssessmentCreateRequest,
        SessionAssessmentCreateRequest> {


    @Mapping(target = "assessmentType",
            source = "assessmentType",
            qualifiedByName = "toReference")
    @Mapping(target = "session",
            source = "session",
            qualifiedByName = "toReference")
    @Override
    void update(SessionAssessmentCreateRequest sessionAssessmentCreateRequest, @MappingTarget SessionAssessment sessionAssessment);


    @Mapping(target = "assessmentType",
            source = "assessmentType",
            qualifiedByName = "toReference")
    @Mapping(target = "session",
            source = "session",
            qualifiedByName = "toReference")
    @Override
    SessionAssessment toEntity(SessionAssessmentCreateRequest sessionAssessmentCreateRequest);


}