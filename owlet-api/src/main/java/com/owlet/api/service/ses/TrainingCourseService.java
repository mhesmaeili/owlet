package com.owlet.api.service.ses;


import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface TrainingCourseService extends CrudService<
        UUID,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest> {

    List<SessionDto> teacherSteamCourse(UUID classroomId);

    Long countOfActiveClasses(UUID schoolId);

    List<TrainingCourseDto> getByStudentId(UUID studentId);

    List<AttachmentUrlDto> getStudentGallery(UUID studentId);
}