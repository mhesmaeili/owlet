package com.owlet.api.service.org;


import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface ClassroomService extends CrudService<
        UUID,
        ClassroomDto,
        ClassroomCreateRequest,
        ClassroomCreateRequest> {
}