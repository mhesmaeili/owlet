package com.owlet.api.service.org.impl;

import com.owlet.api.constant.RoleConst;
import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolCreateRequest;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.org.SchoolUpdateRequest;
import com.owlet.api.mapper.org.SchoolMapper;
import com.owlet.api.repository.org.SchoolRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.SchoolService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SchoolServiceImpl extends CrudServiceImpl<
        School,
        UUID,
        SchoolDto,
        SchoolCreateRequest,
        SchoolUpdateRequest,
        SchoolRepository,
        SchoolMapper>
        implements SchoolService {

    public SchoolServiceImpl(
            SchoolRepository repository,
            SchoolMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper ,  auditableService);
    }

    @Override
    protected String[] getSearchableFields() {
        return new String[]{
                "title",
                "code",
                "schoolType.title"
        };
    }

    @Override
    protected Class<School> entityClass() {
        return School.class;
    }

    @Override
    public List<SchoolDto> teacherSteamWorkWithSchool() {
        List<School> list = repository.findSchoolByTeacherId(auditableService.currentUserId() , RoleConst.ROLE_STEAM_TEACHER);
        return mapper.toDto(list);
    }
}