package com.owlet.api.mapper.edu;

import com.owlet.api.domain.edu.AssessmentTemplate;
import com.owlet.api.dto.edu.AssessmentTemplateCreateRequest;
import com.owlet.api.dto.edu.AssessmentTemplateDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface AssessmentTemplateMapper extends CrudMapper<
        AssessmentTemplate,
        AssessmentTemplateDto,
        AssessmentTemplateCreateRequest,
        AssessmentTemplateCreateRequest> {


}