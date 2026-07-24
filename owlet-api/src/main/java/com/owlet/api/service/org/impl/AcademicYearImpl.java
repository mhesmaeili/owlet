package com.owlet.api.service.org.impl;

import com.owlet.api.domain.org.AcademicYear;
import com.owlet.api.dto.org.AcademicYearCreateRequest;
import com.owlet.api.dto.org.AcademicYearDto;
import com.owlet.api.mapper.org.AcademicYearMapper;
import com.owlet.api.repository.org.AcademicYearRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.org.AcademicYearService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AcademicYearImpl extends CrudServiceImpl<
        AcademicYear,
        UUID,
        AcademicYearDto,
        AcademicYearCreateRequest,
        AcademicYearCreateRequest,
        AcademicYearRepository,
        AcademicYearMapper>
        implements AcademicYearService {

    public AcademicYearImpl(
            AcademicYearRepository repository,
            AcademicYearMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<AcademicYear> entityClass() {
        return AcademicYear.class;
    }
}