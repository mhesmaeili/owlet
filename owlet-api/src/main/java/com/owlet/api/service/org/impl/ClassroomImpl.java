package com.owlet.api.service.org.impl;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.mapper.org.ClassroomMapper;
import com.owlet.api.repository.org.ClassroomRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.ClassroomService;
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
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


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
    public List<ClassroomDto> teacherSteamClassroom(UUID schoolId) {
        List<Classroom> list = repository.teacherSteamClassroom(auditableService.currentUserId(), schoolId);
        return mapper.toDto(list);
    }
}