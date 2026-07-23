package com.owlet.api.service.ref;

import com.owlet.api.dto.ref.ReferenceTypeDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface ReferenceTypeService extends CrudService<
        UUID,
        ReferenceTypeDto,
        ReferenceTypeDto,
        ReferenceTypeDto> {
}