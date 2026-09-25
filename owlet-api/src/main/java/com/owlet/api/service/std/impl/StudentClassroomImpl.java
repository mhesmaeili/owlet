package com.owlet.api.service.std.impl;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.domain.std.StudentClassroom;
import com.owlet.api.dto.org.ClassroomDto;
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
import java.util.Objects;
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
        return mapper.toDto(repository.findByClassroomIdAndDeletedFalse(classroomId));
    }

    @Transactional
    public void updateBatchStatus(List<UUID> ids, Boolean active) {
        repository.updateStatusByIds(ids, active);
    }

    @Override
    public Classroom getClassroomByStudentId(UUID studentId) {
        StudentClassroom studentClassroom = repository.findByStudentIdAndActiveTrue(studentId);
        return studentClassroom.getClassroom();
    }

    @Override
    protected List<StudentClassroom> beforeCreateSaveAll(List<StudentClassroom> studentClassrooms, List<StudentClassroomCreateRequest> list) {
        List<UUID> studentIds = studentClassrooms.stream()
                .map(sc -> sc.getStudent() != null ? sc.getStudent().getId() : null)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        if (!studentIds.isEmpty()) {
            repository.deactivatePreviousAssignments(studentIds);
        }
        return studentClassrooms;
    }

    @Override
    protected String[] getSearchableFields() {
        return new String[]{
                "student.firstName",
                "student.lastName",
                "student.nationalCode",
                "classroom.title",
                "classroom.school.title"
        };
    }

    // متد دریافت شناسه‌های تمام دانش‌آموزانی که در هر کلاسی فعال هستند
    @Override
    public List<UUID> getActiveStudentIds() {
        return repository.findActiveStudentIds();
    }

}