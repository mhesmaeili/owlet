package com.owlet.api.service.ses;


import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface SessionStudentService extends CrudService<
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest> {

    List<SessionStudentDto> updateAttendance(UUID sessionId, List<UUID> studentIds, boolean present);
}