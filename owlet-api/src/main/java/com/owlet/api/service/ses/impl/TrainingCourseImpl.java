package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.Session;
import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.mapper.ses.SessionMapper;
import com.owlet.api.mapper.ses.TrainingCourseMapper;
import com.owlet.api.repository.ses.TrainingCourseRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.SessionService;
import com.owlet.api.service.ses.TrainingCourseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TrainingCourseImpl extends CrudServiceImpl<
        TrainingCourse,
        UUID,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest,
        TrainingCourseRepository,
        TrainingCourseMapper>
        implements TrainingCourseService {

    public TrainingCourseImpl(
            TrainingCourseRepository repository,
            TrainingCourseMapper mapper,
            AuditableService auditableService, SessionMapper sessionMapper, SessionService sessionService) {

        super(repository, mapper, auditableService);
        this.sessionMapper = sessionMapper;
        this.sessionService = sessionService;
    }

    private final SessionMapper sessionMapper;
    private final SessionService sessionService;


    @Override
    protected Class<TrainingCourse> entityClass() {
        return TrainingCourse.class;
    }

    @Override
    public List<SessionDto> teacherSteamCourse(UUID classroomId) {
        List<Session> list = repository.teacherSteamCourse(auditableService.currentUserId(), classroomId);
        return sessionMapper.toDto(list);
    }

    @Override
    public Long countOfActiveClasses(UUID schoolId) {
        return repository.countDistinctClassroomsBySchoolAndTeacher(schoolId, auditableService.currentUserId());
    }

    @Override
    protected void afterCreate(TrainingCourse entity) {
        sessionService.addByTrainingCourse(entity);
        super.afterCreate(entity);
    }
}