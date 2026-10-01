package com.owlet.api.service.base;

import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.dto.base.AttachmentReferenceDto;
import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.storage.StorageObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AttachmentReferenceService extends CrudService<
        UUID,
        AttachmentReferenceDto,
        AttachmentReferenceCreateRequest,
        AttachmentReferenceCreateRequest> {

    AttachmentReferenceDto upload(
            MultipartFile file,
            AttachmentReferenceCreateRequest request);

    List<AttachmentReferenceDto> list(
            String entityClass,
            UUID entityId);

    StorageObject download(UUID id);

    AttachmentUrlDto generatePresignedUrl(AttachmentReferenceDto attachmentReferenceDto);


    List<AttachmentUrlDto> generatePresignedUrlGroup(List<AttachmentReferenceDto> attachmentReferenceDtoList);

    List<AttachmentReferenceDto> findByStudentId(UUID studentId , Integer limit);

    List<AttachmentReferenceDto> findByStudentIdAndCourseId(UUID studentId, UUID courseId);

    List<AttachmentReferenceDto> findBySessionId(UUID sessionId);

    List<AttachmentUrlDto> findByEntityId(UUID entityId);

    List<AttachmentUrlDto> findByEntityId(
            UUID entityId,
            Integer limit
    );

    AttachmentUrlDto findPrimaryByProductId(UUID entityId);

    AttachmentUrlDto generatePresignedUrl(UUID attachmentId);
}