package com.owlet.api.service.org.impl;

import com.owlet.api.domain.org.SchoolMember;
import com.owlet.api.dto.org.SchoolMemberCreateRequest;
import com.owlet.api.dto.org.SchoolMemberDto;
import com.owlet.api.mapper.org.SchoolMemberMapper;
import com.owlet.api.repository.org.SchoolMemberRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.SchoolMemberService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class SchoolMemberImpl extends CrudServiceImpl<
        SchoolMember,
        UUID,
        SchoolMemberDto,
        SchoolMemberCreateRequest,
        SchoolMemberCreateRequest,
        SchoolMemberRepository,
        SchoolMemberMapper>
        implements SchoolMemberService {

    public SchoolMemberImpl(
            SchoolMemberRepository repository,
            SchoolMemberMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<SchoolMember> entityClass() {
        return SchoolMember.class;
    }
}