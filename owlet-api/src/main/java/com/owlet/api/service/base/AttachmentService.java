package com.owlet.api.service.base;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.storage.StorageObject;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.util.UUID;

public interface AttachmentService extends CrudService<
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {

    AttachmentDto upload(
            MultipartFile file,
            AttachmentReferenceCreateRequest request);

    StorageObject download(Attachment attachment);

    String generatePresignedUrl(
            String objectKey,
            Duration duration);
}