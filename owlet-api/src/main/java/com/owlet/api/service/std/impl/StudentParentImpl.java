package com.owlet.api.service.std.impl;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.domain.std.StudentParent;
import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.dto.std.StudentParentCreateRequest;
import com.owlet.api.dto.std.StudentParentDto;
import com.owlet.api.mapper.std.StudentMapper;
import com.owlet.api.mapper.std.StudentParentMapper;
import com.owlet.api.repository.std.StudentParentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentClassroomService;
import com.owlet.api.service.std.StudentParentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StudentParentImpl extends CrudServiceImpl<
        StudentParent,
        UUID,
        StudentParentDto,
        StudentParentCreateRequest,
        StudentParentCreateRequest,
        StudentParentRepository,
        StudentParentMapper>
        implements StudentParentService {

    public StudentParentImpl(
            StudentParentRepository repository,
            StudentParentMapper mapper,
            AuditableService auditableService, StudentMapper studentMapper, StudentClassroomService studentClassroomService) {

        super(repository, mapper, auditableService);
        this.studentMapper = studentMapper;
        this.studentClassroomService = studentClassroomService;
    }

    private final StudentMapper studentMapper;
    private final StudentClassroomService studentClassroomService;


    @Override
    protected Class<StudentParent> entityClass() {
        return StudentParent.class;
    }

    @Override
    public List<StudentDto> getStudent() {
        List<StudentDto> dtos = studentMapper.toDto(repository.findStudentByParentId(auditableService.currentUserId()));

        dtos.forEach(studentDto -> {
            Classroom classroom = studentClassroomService.getClassroomByStudentId(studentDto.getId());
            studentDto.setClassName(classroom.getTitle());
        });

        return dtos;
    }
}