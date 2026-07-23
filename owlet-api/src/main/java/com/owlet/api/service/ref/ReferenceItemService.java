package com.owlet.api.service.ref;

import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface ReferenceItemService extends CrudService<
        UUID,
        ReferenceItemDto,
        ReferenceItemDto,
        ReferenceItemDto> {
}