package com.owlet.api.service.std;


import com.owlet.api.dto.std.StudentParentCreateRequest;
import com.owlet.api.dto.std.StudentParentDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface StudentParentService extends CrudService<
        UUID,
        StudentParentDto,
        StudentParentCreateRequest,
        StudentParentCreateRequest> {
}