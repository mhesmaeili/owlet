package com.owlet.api.service.ses.impl;

import com.owlet.api.constant.ReferenceItemCode;
import com.owlet.api.constant.ReferenceType;
import com.owlet.api.domain.ses.Session;
import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.dto.profile.parent.StudentGalleryGroupDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.mapper.ses.SessionMapper;
import com.owlet.api.mapper.ses.TrainingCourseMapper;
import com.owlet.api.repository.ses.TrainingCourseRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.AttachmentReferenceService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.helper.EntityIdDto;
import com.owlet.api.service.ref.ReferenceItemService;
import com.owlet.api.service.ses.SessionService;
import com.owlet.api.service.ses.TrainingCourseService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class TrainingCourseImpl extends CrudServiceImpl<
        TrainingCourse,
        UUID,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest,
        TrainingCourseRepository,
        TrainingCourseMapper>
        implements TrainingCourseService {

    public TrainingCourseImpl(
            TrainingCourseRepository repository,
            TrainingCourseMapper mapper,
            AuditableService auditableService, SessionMapper sessionMapper, SessionService sessionService, ReferenceItemService referenceItemService, AttachmentReferenceService attachmentReferenceService, EntityManager entityManager) {

        super(repository, mapper, auditableService);
        this.sessionMapper = sessionMapper;
        this.sessionService = sessionService;
        this.referenceItemService = referenceItemService;
        this.attachmentReferenceService = attachmentReferenceService;
        this.entityManager = entityManager;
    }

    private final SessionMapper sessionMapper;
    private final SessionService sessionService;
    private final ReferenceItemService referenceItemService;
    private final AttachmentReferenceService attachmentReferenceService;
    private final EntityManager entityManager;


    @Override
    protected Class<TrainingCourse> entityClass() {
        return TrainingCourse.class;
    }

    @Override
    public List<SessionDto> teacherSteamCourse(UUID classroomId) {
        List<Session> list = repository.teacherSteamCourse(auditableService.currentUserId(), classroomId);
        return sessionMapper.toDto(list);
    }

    @Override
    public Long countOfActiveClasses(UUID schoolId) {
        return repository.countDistinctClassroomsBySchoolAndTeacher(schoolId, auditableService.currentUserId());
    }

    @Override
    public List<TrainingCourseDto> getByStudentId(UUID studentId) {
        return mapper.toDto(repository.findAllByStudentId(studentId));
    }

    @Override
    public List<StudentGalleryGroupDto> getStudentGallery(UUID studentId, List<UUID> courseIds) {
        List<StudentGalleryGroupDto> list = new ArrayList<>();
        courseIds.forEach(courseId -> {
            List<AttachmentUrlDto> attachmentUrlDtos = attachmentReferenceService.generatePresignedUrlGroup(attachmentReferenceService.findByStudentIdAndCourseId(studentId, courseId));

            TrainingCourse reference = entityManager.getReference(TrainingCourse.class, courseId);

            StudentGalleryGroupDto dto = new StudentGalleryGroupDto(reference.getProduct().getTitle(), attachmentUrlDtos);
            list.add(dto);
        });
        return list;
    }

    @Override
    public List<AttachmentUrlDto> getRecentGallery(UUID studentId, Integer limit) {
        return attachmentReferenceService.generatePresignedUrlGroup(attachmentReferenceService.findByStudentId(studentId, limit));
    }

    @Override
    protected void beforeCreate(TrainingCourseCreateRequest dto) {
        ReferenceItemDto referenceItemDto = referenceItemService.getByTypeCodeAndItemCode(ReferenceType.TRAINING_COURSE_STATUS, ReferenceItemCode.IN_PROGRESS);
        dto.setTrainingStatus(new EntityIdDto(referenceItemDto.getId()));
        super.beforeCreate(dto);
    }

    @Override
    protected void afterCreate(TrainingCourse entity) {
        sessionService.addByTrainingCourse(entity);
        super.afterCreate(entity);
    }
}