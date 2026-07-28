package com.owlet.api.service.edu;

import com.owlet.api.dto.edu.AssessmentTemplateCreateRequest;
import com.owlet.api.dto.edu.AssessmentTemplateDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface AssessmentTemplateService extends CrudService<
        UUID,
        AssessmentTemplateDto,
        AssessmentTemplateCreateRequest,
        AssessmentTemplateCreateRequest> {
}