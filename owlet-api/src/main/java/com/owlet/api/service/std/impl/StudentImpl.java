package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.Student;
import com.owlet.api.dto.std.StudentCreateRequest;
import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.mapper.std.StudentMapper;
import com.owlet.api.repository.std.StudentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;

import com.owlet.api.service.std.StudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StudentImpl extends CrudServiceImpl<
        Student,
        UUID,
        StudentDto,
        StudentCreateRequest,
        StudentCreateRequest,
        StudentRepository,
        StudentMapper>
        implements StudentService {

    public StudentImpl(
            StudentRepository repository,
            StudentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<Student> entityClass() {
        return Student.class;
    }
}