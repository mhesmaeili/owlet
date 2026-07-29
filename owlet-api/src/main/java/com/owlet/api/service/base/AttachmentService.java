package com.owlet.api.service.base;

import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public interface AttachmentService extends CrudService<
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {

    AttachmentDto upload(
            MultipartFile file,
            AttachmentCreateRequest request);

    List<AttachmentDto> list(
            String entityClass,
            UUID entityId);

    InputStream download(UUID id);
}