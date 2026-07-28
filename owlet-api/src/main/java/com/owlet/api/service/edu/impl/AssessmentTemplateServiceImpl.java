package com.owlet.api.service.edu.impl;

import com.owlet.api.domain.edu.AssessmentTemplate;
import com.owlet.api.dto.edu.AssessmentTemplateCreateRequest;
import com.owlet.api.dto.edu.AssessmentTemplateDto;
import com.owlet.api.mapper.edu.AssessmentTemplateMapper;
import com.owlet.api.repository.edu.AssessmentTemplateRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.edu.AssessmentTemplateService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AssessmentTemplateServiceImpl extends CrudServiceImpl<
        AssessmentTemplate,
        UUID,
        AssessmentTemplateDto,
        AssessmentTemplateCreateRequest,
        AssessmentTemplateCreateRequest,
        AssessmentTemplateRepository,
        AssessmentTemplateMapper>
        implements AssessmentTemplateService {

    public AssessmentTemplateServiceImpl(
            AssessmentTemplateRepository repository,
            AssessmentTemplateMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }

    @Override
    protected Class<AssessmentTemplate> entityClass() {
        return AssessmentTemplate.class;
    }
}