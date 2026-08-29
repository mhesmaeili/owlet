package com.owlet.api.service.ref;

import com.owlet.api.dto.ref.ReferenceItemCreateRequest;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface ReferenceItemService extends CrudService<
        UUID,
        ReferenceItemDto,
        ReferenceItemCreateRequest,
        ReferenceItemCreateRequest> {
    List<ReferenceItemDto> loadByTypeCode(String typeCode);

    ReferenceItemDto getByTypeCodeAndItemCode(String typeCode, String itemCode);
}