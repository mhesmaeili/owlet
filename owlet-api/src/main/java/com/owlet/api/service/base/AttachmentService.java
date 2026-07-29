package com.owlet.api.service.base;

import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;

import java.util.UUID;

public interface AttachmentService extends CrudService<
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {
}