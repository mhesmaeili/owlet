package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.StudentHomeActivity;
import com.owlet.api.dto.std.StudentHomeActivityCreateRequest;
import com.owlet.api.dto.std.StudentHomeActivityDto;
import com.owlet.api.mapper.std.StudentHomeActivityMapper;
import com.owlet.api.repository.std.StudentHomeActivityRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentHomeActivityService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StudentHomeActivityImpl extends CrudServiceImpl<
        StudentHomeActivity,
        UUID,
        StudentHomeActivityDto,
        StudentHomeActivityCreateRequest,
        StudentHomeActivityCreateRequest,
        StudentHomeActivityRepository,
        StudentHomeActivityMapper>
        implements StudentHomeActivityService {

    public StudentHomeActivityImpl(
            StudentHomeActivityRepository repository,
            StudentHomeActivityMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<StudentHomeActivity> entityClass() {
        return StudentHomeActivity.class;
    }

    @Override
    public StudentHomeActivityDto getByStudentAndCourse(UUID studentId, UUID courseId) {
        return toDto(repository.findByTrainingCourseIdAndStudentId(courseId, studentId));
    }
}