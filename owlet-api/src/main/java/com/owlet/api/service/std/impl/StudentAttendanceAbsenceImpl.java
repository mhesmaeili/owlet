package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.StudentAttendanceAbsence;
import com.owlet.api.dto.std.StudentAttendanceAbsenceCreateRequest;
import com.owlet.api.dto.std.StudentAttendanceAbsenceDto;
import com.owlet.api.mapper.std.StudentAttendanceAbsenceMapper;
import com.owlet.api.repository.std.StudentAttendanceAbsenceRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentAttendanceAbsenceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StudentAttendanceAbsenceImpl extends CrudServiceImpl<
        StudentAttendanceAbsence,
        UUID,
        StudentAttendanceAbsenceDto,
        StudentAttendanceAbsenceCreateRequest,
        StudentAttendanceAbsenceCreateRequest,
        StudentAttendanceAbsenceRepository,
        StudentAttendanceAbsenceMapper>
        implements StudentAttendanceAbsenceService {

    public StudentAttendanceAbsenceImpl(
            StudentAttendanceAbsenceRepository repository,
            StudentAttendanceAbsenceMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<StudentAttendanceAbsence> entityClass() {
        return StudentAttendanceAbsence.class;
    }
}