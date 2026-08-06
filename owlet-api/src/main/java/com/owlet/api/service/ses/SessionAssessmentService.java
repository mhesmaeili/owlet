package com.owlet.api.service.ses;


import com.owlet.api.dto.ses.SessionAssessmentCreateRequest;
import com.owlet.api.dto.ses.SessionAssessmentDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface SessionAssessmentService extends CrudService<
        UUID,
        SessionAssessmentDto,
        SessionAssessmentCreateRequest,
        SessionAssessmentCreateRequest> {

}