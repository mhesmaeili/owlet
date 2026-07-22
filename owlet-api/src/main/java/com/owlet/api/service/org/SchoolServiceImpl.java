package com.owlet.api.service.org;

import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.mapper.org.SchoolMapper;
import com.owlet.api.repository.org.SchoolRepository;
import com.owlet.api.repository.specification.SpecificationBuilder;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SchoolServiceImpl extends CrudServiceImpl<
        School,
        UUID,
        SchoolDto,
        SchoolDto,
        SchoolDto,
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
    protected Specification<School> buildSearchSpecification(
            String keyword) {


        return SpecificationBuilder.contains(
                keyword,
                "name",
                "code"
        );

    }

    @Override
    protected Class<School> entityClass() {
        return School.class;
    }
}