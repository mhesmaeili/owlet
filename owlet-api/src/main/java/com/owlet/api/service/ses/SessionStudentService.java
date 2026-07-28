package com.owlet.api.service.ses;


import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface SessionStudentService extends CrudService<
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest> {
}