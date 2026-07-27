package com.owlet.api.service.ses;


import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface SessionService extends CrudService<
        UUID,
        SessionDto,
        SessionCreateRequest,
        SessionCreateRequest> {
}