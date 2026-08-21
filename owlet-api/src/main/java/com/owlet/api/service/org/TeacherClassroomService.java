package com.owlet.api.service.org;


import com.owlet.api.dto.org.TeacherClassroomCreateRequest;
import com.owlet.api.dto.org.TeacherClassroomDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface TeacherClassroomService extends CrudService<
        UUID,
        TeacherClassroomDto,
        TeacherClassroomCreateRequest,
        TeacherClassroomCreateRequest> {
    Long countOfActiveClasses(UUID schoolId);
}