package com.owlet.api.service.ses;


import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.service.base.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SessionStudentService extends CrudService<
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest> {

    List<SessionStudentDto> updateAttendance(UUID sessionId, List<UUID> studentIds, Boolean present);

    Page<SessionStudentDto> getStudentsBySession(UUID sessionId, String keyword, Pageable pageable);

    SessionStudentDto updatePoint(UUID id, Integer point , String pointDescription);

    SessionStudentDto updateTimeBase(UUID id, Boolean timeBase);

    SessionStudentDto updateNumber(UUID id, Integer number);

    SessionStudentDto updateStateEvaluation(UUID id, String stateEvaluation);

    Long countOfStudentByClassroomId(UUID classroomId);

    List<SessionStudentDto> studentListBySessionId(UUID sessionId);

    SessionStudentDto updateSoftSkillsSelected(UUID id, List<String> softSkillsSelected);

    SessionStudentDto updateElapsedTime(UUID id, Integer elapsedTime);

    List<SessionStudentDto> getMediaStatusBySession(UUID sessionId);

    void addBySession(List<SessionDto> listAdded, TrainingCourse entity);
}