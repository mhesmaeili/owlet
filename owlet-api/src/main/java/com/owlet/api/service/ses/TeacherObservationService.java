package com.owlet.api.service.ses;


import com.owlet.api.dto.ses.TeacherObservationCreateRequest;
import com.owlet.api.dto.ses.TeacherObservationDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface TeacherObservationService extends CrudService<
        UUID,
        TeacherObservationDto,
        TeacherObservationCreateRequest,
        TeacherObservationCreateRequest> {
    List<TeacherObservationDto> getBySessionId(UUID sessionId);
}