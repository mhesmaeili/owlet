package com.owlet.api.service.std;



import com.owlet.api.dto.std.StudentBadgeCreateRequest;
import com.owlet.api.dto.std.StudentBadgeDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface StudentBadgeService extends CrudService<
        UUID,
        StudentBadgeDto,
        StudentBadgeCreateRequest,
        StudentBadgeCreateRequest> {
}