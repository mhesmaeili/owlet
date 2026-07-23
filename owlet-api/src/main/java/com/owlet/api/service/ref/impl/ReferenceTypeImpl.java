package com.owlet.api.service.ref.impl;

import com.owlet.api.domain.ref.ReferenceType;
import com.owlet.api.dto.ref.ReferenceTypeDto;
import com.owlet.api.mapper.ref.ReferenceTypeMapper;
import com.owlet.api.repository.ref.ReferenceTypeRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ref.ReferenceTypeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ReferenceTypeImpl extends CrudServiceImpl<
        ReferenceType,
        UUID,
        ReferenceTypeDto,
        ReferenceTypeDto,
        ReferenceTypeDto,
        ReferenceTypeRepository,
        ReferenceTypeMapper>
        implements ReferenceTypeService {

    public ReferenceTypeImpl(
            ReferenceTypeRepository repository,
            ReferenceTypeMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<ReferenceType> entityClass() {
        return ReferenceType.class;
    }

    @Override
    protected ReferenceType beforeCreateSave(ReferenceType entity, ReferenceTypeDto dto) {
        entity.setActive(true);
        return super.beforeCreateSave(entity, dto);
    }
}