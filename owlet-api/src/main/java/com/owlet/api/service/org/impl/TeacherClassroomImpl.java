package com.owlet.api.service.org.impl;

import com.owlet.api.domain.org.TeacherClassroom;
import com.owlet.api.dto.org.TeacherClassroomCreateRequest;
import com.owlet.api.dto.org.TeacherClassroomDto;
import com.owlet.api.mapper.org.TeacherClassroomMapper;
import com.owlet.api.repository.org.TeacherClassroomRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.TeacherClassroomService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class TeacherClassroomImpl extends CrudServiceImpl<
        TeacherClassroom,
        UUID,
        TeacherClassroomDto,
        TeacherClassroomCreateRequest,
        TeacherClassroomCreateRequest,
        TeacherClassroomRepository,
        TeacherClassroomMapper>
        implements TeacherClassroomService {

    public TeacherClassroomImpl(
            TeacherClassroomRepository repository,
            TeacherClassroomMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<TeacherClassroom> entityClass() {
        return TeacherClassroom.class;
    }

    @Override
    public Long countOfActiveClasses(UUID schoolId) {
        return repository.countByClassroom_School_IdAndTeacherAccount_IdAndActiveTrue(schoolId, auditableService.currentUserId());
    }
}