package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.SessionAssessmentCreateRequest;
import com.owlet.api.dto.ses.SessionAssessmentDto;
import com.owlet.api.service.ses.SessionAssessmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "SessionAssessmentController")
@RestController
@RequestMapping("/api/ses/sessionAssessment")
public class SessionAssessmentController extends CrudController<
        UUID,
        SessionAssessmentDto,
        SessionAssessmentCreateRequest,
        SessionAssessmentCreateRequest> {

    public SessionAssessmentController(SessionAssessmentService service) {
        super(service);
    }

}