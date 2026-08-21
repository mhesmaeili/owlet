package com.owlet.api.service.org.impl;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.dto.profile.school.ProfileTeacherClassroomDto;
import com.owlet.api.mapper.org.ClassroomMapper;
import com.owlet.api.mapper.profile.school.ProfileTeacherClassroomMapper;
import com.owlet.api.repository.org.ClassroomRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.ClassroomService;
import com.owlet.api.service.ses.SessionStudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ClassroomImpl extends CrudServiceImpl<
        Classroom,
        UUID,
        ClassroomDto,
        ClassroomCreateRequest,
        ClassroomCreateRequest,
        ClassroomRepository,
        ClassroomMapper>
        implements ClassroomService {

    public ClassroomImpl(
            ClassroomRepository repository,
            ClassroomMapper mapper,
            AuditableService auditableService, ProfileTeacherClassroomMapper profileTeacherClassroomMapper, SessionStudentService sessionStudentService) {

        super(repository, mapper, auditableService);
        this.profileTeacherClassroomMapper = profileTeacherClassroomMapper;
        this.sessionStudentService = sessionStudentService;
    }

    private final ProfileTeacherClassroomMapper profileTeacherClassroomMapper;
    private final SessionStudentService sessionStudentService;


    @Override
    protected Class<Classroom> entityClass() {
        return Classroom.class;
    }

    @Override
    public List<ClassroomDto> findByTitle(String title) {
        List<Classroom> list = repository.findByTitle(title);
        return mapper.toDto(list);
    }

    @Override
    public List<ProfileTeacherClassroomDto> teacherSteamClassroom(UUID schoolId) {
        List<Classroom> list = repository.teacherSteamClassroom(auditableService.currentUserId(), schoolId);
        List<ProfileTeacherClassroomDto> dtos = profileTeacherClassroomMapper.toDto(list);

        dtos.forEach(dto -> {
            dto.setCapacity(sessionStudentService.countOfStudentByClassroomId(dto.getId()));
        });

        return dtos;
    }

    @Override
    public Long countOfActiveClasses(UUID schoolId) {
        return repository.countBySchoolIdAndTeacherAccountIdAndActiveTrue(schoolId, auditableService.currentUserId());
    }
}