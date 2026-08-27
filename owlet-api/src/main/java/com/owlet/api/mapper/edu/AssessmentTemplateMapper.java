package com.owlet.api.mapper.edu;

import com.owlet.api.domain.edu.AssessmentTemplate;
import com.owlet.api.dto.edu.AssessmentTemplateCreateRequest;
import com.owlet.api.dto.edu.AssessmentTemplateDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface AssessmentTemplateMapper extends CrudMapper<
        AssessmentTemplate,
        AssessmentTemplateDto,
        AssessmentTemplateCreateRequest,
        AssessmentTemplateCreateRequest> {


    @Mapping(target = "assessmentType",
            source = "assessmentType",
            qualifiedByName = "toReference")
    @Mapping(target = "sessionType",
            source = "sessionType",
            qualifiedByName = "toReference")
    @Mapping(target = "product",
            source = "product",
            qualifiedByName = "toReference")
    @Override
    void update(AssessmentTemplateCreateRequest assessmentTemplateCreateRequest, @MappingTarget AssessmentTemplate assessmentTemplate);



    @Mapping(target = "assessmentType",
            source = "assessmentType",
            qualifiedByName = "toReference")
    @Mapping(target = "sessionType",
            source = "sessionType",
            qualifiedByName = "toReference")
    @Mapping(target = "product",
            source = "product",
            qualifiedByName = "toReference")
    @Override
    AssessmentTemplate toEntity(AssessmentTemplateCreateRequest assessmentTemplateCreateRequest);
}