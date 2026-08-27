package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.Session;
import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.dto.std.StudentClassroomDto;
import com.owlet.api.mapper.ses.SessionStudentMapper;
import com.owlet.api.repository.base.AttachmentReferenceRepository;
import com.owlet.api.repository.ses.SessionStudentRepository;
import com.owlet.api.repository.specification.FilterNode;
import com.owlet.api.repository.specification.SearchOperation;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.service.ses.SessionStudentService;
import com.owlet.api.service.std.StudentClassroomService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;

@Service
@Transactional
public class SessionStudentImpl extends CrudServiceImpl<
        SessionStudent,
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest,
        SessionStudentRepository,
        SessionStudentMapper>
        implements SessionStudentService {

    public SessionStudentImpl(
            SessionStudentRepository repository,
            SessionStudentMapper mapper,
            AuditableService auditableService, AttachmentReferenceRepository attachmentReferenceRepository, StudentClassroomService studentClassroomService) {

        super(repository, mapper, auditableService);

        this.attachmentReferenceRepository = attachmentReferenceRepository;
        this.studentClassroomService = studentClassroomService;
    }

    private final AttachmentReferenceRepository attachmentReferenceRepository;
    private final StudentClassroomService studentClassroomService;


    @Override
    protected Class<SessionStudent> entityClass() {
        return SessionStudent.class;
    }

    @Transactional
    @Override
    public List<SessionStudentDto> updateAttendance(UUID sessionId, List<UUID> studentIds, Boolean present) {
        List<SessionStudent> list = repository.findBySession_IdAndStudent_IdIn(sessionId, studentIds);
        list.forEach(ss -> {
            ss.setPresent(present);
            ss.setAttendanceTime(OffsetDateTime.now());
        });

        return mapper.toDto(list);
    }

    @Override
    protected String[] getSearchableFields() {
        return new String[]{
                "student.firstName",
                "student.lastName",
                "student.studentNo",
                "student.nationalCode"
        };
    }

    @Override
    public Page<SessionStudentDto> getStudentsBySession(UUID sessionId, String keyword, Pageable pageable) {

        FilterNode filterTree = FilterNode.and(
                FilterNode.condition("session.id", SearchOperation.EQUAL, sessionId)
        );

        return searchAdvanced(keyword, filterTree, pageable);
    }

    @Override
    @Transactional
    public SessionStudentDto updatePoint(UUID id, Integer point, String pointDescription) {
        SessionStudent entity = findEntity(id);
        entity.setPoint(point);
        entity.setPointDescription(pointDescription);
        return toDto(entity);
    }

    @Override
    @Transactional
    public SessionStudentDto updateTimeBase(UUID id, Boolean timeBase) {
        SessionStudent entity = findEntity(id);
        entity.setTimeBase(timeBase);
        return toDto(entity);
    }

    @Override
    @Transactional
    public SessionStudentDto updateNumber(UUID id, Integer number) {
        SessionStudent entity = findEntity(id);
        entity.setNumber(number);
        return toDto(entity);
    }

    @Override
    @Transactional
    public SessionStudentDto updateStateEvaluation(UUID id, String stateEvaluation) {
        SessionStudent entity = findEntity(id);

        /*String joinedStates = (stateEvaluationSelected != null && !stateEvaluationSelected.isEmpty())
                ? String.join(",", stateEvaluationSelected)
                : null;*/

        entity.setStateEvaluation(stateEvaluation);
        return toDto(entity);
    }

    @Override
    public Long countOfStudentByClassroomId(UUID classroomId) {
        return repository.countByClassroomId(classroomId);
    }

    @Override
    public List<SessionStudentDto> studentListBySessionId(UUID sessionId) {
        List<SessionStudent> list = repository.findBySession_id(sessionId);
        return mapper.toDto(list);
    }

    @Override
    public SessionStudentDto updateSoftSkillsSelected(UUID id, List<String> softSkillsSelected) {
        SessionStudent entity = findEntity(id);

        /*String joinedStates = (stateEvaluationSelected != null && !stateEvaluationSelected.isEmpty())
                ? String.join(",", stateEvaluationSelected)
                : null;*/

        entity.setSoftSkillsSelected(softSkillsSelected);
        return toDto(entity);
    }

    @Override
    public SessionStudentDto updateElapsedTime(UUID id, Integer elapsedTime) {
        SessionStudent entity = findEntity(id);
        entity.setElapsedTime(elapsedTime);
        return toDto(entity);
    }

    @Override
    public List<SessionStudentDto> getMediaStatusBySession(UUID sessionId) {

        List<SessionStudent> sessionStudents = repository.findBySessionId(sessionId);
        List<SessionStudentDto> dtoList = new ArrayList<>();

        for (SessionStudent entity : sessionStudents) {
            SessionStudentDto dto = mapper.toDto(entity);

            OffsetDateTime globalLastPhotoDate = attachmentReferenceRepository
                    .findLastPhotoDateByStudentId(entity.getStudent().getId())
                    .orElse(null);

            dto.setLastPhotoDate(globalLastPhotoDate);

            dtoList.add(dto);
        }

        return dtoList;
    }

    @Override
    public void addBySession(List<SessionDto> listAdded, TrainingCourse entity) {
        List<StudentClassroomDto> studentClassroomDtos = studentClassroomService.findByClassroomId(entity.getClassroom().getId());

        List<SessionStudentCreateRequest> listForAdd = new ArrayList<>();

        listAdded.forEach(session -> {
            studentClassroomDtos.forEach(studentClassroomDto -> {
                SessionStudentCreateRequest dto = new SessionStudentCreateRequest();
                dto.setSession(new EntityIdDto(session.getId()));
                dto.setStudent(new EntityIdDto(studentClassroomDto.getStudent().getId()));
                dto.setPresent(true);
                listForAdd.add(dto);
            });
        });

        create(listForAdd);
    }

    @Override
    public SessionStudentDto updateSliderEvaluations(UUID sessionStudentId, Map<String, Integer> scores) {
        SessionStudent sessionStudent = repository.findById(sessionStudentId).orElseThrow();

        // گرفتن نمرات قبلی (اگر نال بود یک مپ خالی می‌سازیم)
        Map<String, Integer> existingScores = sessionStudent.getSliderEvaluations();
        if (existingScores == null) {
            existingScores = new HashMap<>();
        }

        // اضافه کردن یا آپدیت کردن نمرات جدید روی نمرات قبلی (بدون پاک شدن قدیمی‌ها)
        existingScores.putAll(scores);

        sessionStudent.setSliderEvaluations(existingScores);
        repository.save(sessionStudent);
        return mapper.toDto(sessionStudent);
    }
}