package com.owlet.api.service.org.impl;

import com.owlet.api.domain.org.Branch;
import com.owlet.api.dto.org.BranchCreateRequest;
import com.owlet.api.dto.org.BranchDto;
import com.owlet.api.mapper.org.BranchMapper;
import com.owlet.api.repository.org.BranchRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.BranchService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class BranchServiceImpl extends CrudServiceImpl<
        Branch,
        UUID,
        BranchDto,
        BranchCreateRequest,
        BranchCreateRequest,
        BranchRepository,
        BranchMapper>
        implements BranchService {

    public BranchServiceImpl(
            BranchRepository repository,
            BranchMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<Branch> entityClass() {
        return Branch.class;
    }
}