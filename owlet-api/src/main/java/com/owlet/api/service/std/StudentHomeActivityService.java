package com.owlet.api.service.std;

import com.owlet.api.dto.std.StudentHomeActivityCreateRequest;
import com.owlet.api.dto.std.StudentHomeActivityDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface StudentHomeActivityService extends CrudService<
        UUID,
        StudentHomeActivityDto,
        StudentHomeActivityCreateRequest,
        StudentHomeActivityCreateRequest> {
}