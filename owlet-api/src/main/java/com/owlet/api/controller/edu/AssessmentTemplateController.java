package com.owlet.api.controller.edu;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.edu.AssessmentTemplateCreateRequest;
import com.owlet.api.dto.edu.AssessmentTemplateDto;
import com.owlet.api.service.edu.AssessmentTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "AssessmentTemplateController")
@RestController
@RequestMapping("/api/edu/assessmentTemplate")
public class AssessmentTemplateController extends CrudController<
        UUID,
        AssessmentTemplateDto,
        AssessmentTemplateCreateRequest,
        AssessmentTemplateCreateRequest> {

    public AssessmentTemplateController(AssessmentTemplateService service) {
        super(service);
    }

}