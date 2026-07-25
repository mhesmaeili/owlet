package com.owlet.api.service.org;


import com.owlet.api.dto.org.SchoolMemberCreateRequest;
import com.owlet.api.dto.org.SchoolMemberDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface SchoolMemberService extends CrudService<
        UUID,
        SchoolMemberDto,
        SchoolMemberCreateRequest,
        SchoolMemberCreateRequest> {
}