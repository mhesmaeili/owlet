package com.owlet.api.service.org;

import com.owlet.api.dto.org.BranchCreateRequest;
import com.owlet.api.dto.org.BranchDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface BranchService extends CrudService<
        UUID,
        BranchDto,
        BranchCreateRequest,
        BranchCreateRequest> {
}