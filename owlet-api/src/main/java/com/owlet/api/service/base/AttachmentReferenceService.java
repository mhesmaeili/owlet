package com.owlet.api.service.base;

import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.dto.base.AttachmentReferenceDto;
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
}