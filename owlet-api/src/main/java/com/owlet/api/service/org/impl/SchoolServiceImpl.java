package com.owlet.api.service.org.impl;

import com.owlet.api.constant.RoleConst;
import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolCreateRequest;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.org.SchoolUpdateRequest;
import com.owlet.api.dto.profile.school.TeacherSchoolDto;
import com.owlet.api.mapper.org.SchoolMapper;
import com.owlet.api.mapper.profile.school.TeacherSchoolMapper;
import com.owlet.api.repository.org.SchoolRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.security.CurrentUserService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.SchoolService;
import com.owlet.api.service.ses.TrainingCourseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SchoolServiceImpl extends CrudServiceImpl<
        School,
        UUID,
        SchoolDto,
        SchoolCreateRequest,
        SchoolUpdateRequest,
        SchoolRepository,
        SchoolMapper>
        implements SchoolService {

    public SchoolServiceImpl(
            SchoolRepository repository,
            SchoolMapper mapper,
            AuditableService auditableService, TeacherSchoolMapper teacherSchoolMapper, TrainingCourseService trainingCourseService, CurrentUserService currentUserService) {

        super(repository, mapper, auditableService);
        this.teacherSchoolMapper = teacherSchoolMapper;
        this.trainingCourseService = trainingCourseService;
        this.currentUserService = currentUserService;
    }

    protected final TeacherSchoolMapper teacherSchoolMapper;
    protected final TrainingCourseService trainingCourseService;
    private final CurrentUserService currentUserService;

    @Override
    protected String[] getSearchableFields() {
        return new String[]{
                "title",
                "code",
                "schoolType.title"
        };
    }

    @Override
    protected Class<School> entityClass() {
        return School.class;
    }

    @Override
    public List<TeacherSchoolDto> teacherSteamWorkWithSchool() {
        List<School> list = null;
        if (currentUserService.hasRole("ROLE_" + RoleConst.OWLET_ADMIN)) {
            list = repository.findAllByActiveTrue();
        } else {
            list = repository.findSchoolByTeacherId(auditableService.currentUserId(), RoleConst.ROLE_STEAM_TEACHER);
        }
        List<TeacherSchoolDto> dtos = teacherSchoolMapper.toDto(list);
        dtos.forEach(teacherSchoolDto -> {
            teacherSchoolDto.setActiveClasses(trainingCourseService.countOfActiveClasses(teacherSchoolDto.getId()));
        });

        return dtos;
    }
}