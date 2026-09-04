package com.owlet.api.service.base;


import com.owlet.api.dto.base.ContactMessageCreateRequest;
import com.owlet.api.dto.base.ContactMessageDto;

import java.util.UUID;

public interface ContactMessageService extends CrudService<
        UUID,
        ContactMessageDto,
        ContactMessageCreateRequest,
        ContactMessageCreateRequest> {
}