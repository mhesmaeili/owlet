package com.owlet.api.service.ses;


import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface TrainingCourseService extends CrudService<
        UUID,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest> {
}