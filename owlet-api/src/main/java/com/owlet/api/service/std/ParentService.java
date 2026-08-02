package com.owlet.api.service.std;



import com.owlet.api.dto.std.ParentCreateRequest;
import com.owlet.api.dto.std.ParentDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface ParentService extends CrudService<
        UUID,
        ParentDto,
        ParentCreateRequest,
        ParentCreateRequest> {
}