package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.StudentClassroom;
import com.owlet.api.dto.std.StudentClassroomCreateRequest;
import com.owlet.api.dto.std.StudentClassroomDto;
import com.owlet.api.mapper.std.StudentClassroomMapper;
import com.owlet.api.repository.std.StudentClassroomRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentClassroomService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentClassroomImpl extends CrudServiceImpl<
        StudentClassroom,
        UUID,
        StudentClassroomDto,
        StudentClassroomCreateRequest,
        StudentClassroomCreateRequest,
        StudentClassroomRepository,
        StudentClassroomMapper>
        implements StudentClassroomService {

    public StudentClassroomImpl(
            StudentClassroomRepository repository,
            StudentClassroomMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<StudentClassroom> entityClass() {
        return StudentClassroom.class;
    }

    @Override
    public List<StudentClassroomDto> findByClassroomId(UUID classroomId) {
        return mapper.toDto(repository.findAllByClassroom_Id(classroomId));
    }
}