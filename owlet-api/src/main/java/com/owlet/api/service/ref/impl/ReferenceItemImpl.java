package com.owlet.api.service.ref.impl;

import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.mapper.ref.ReferenceItemMapper;
import com.owlet.api.repository.ref.ReferenceItemRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ref.ReferenceItemService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ReferenceItemImpl extends CrudServiceImpl<
        ReferenceItem,
        UUID,
        ReferenceItemDto,
        ReferenceItemDto,
        ReferenceItemDto,
        ReferenceItemRepository,
        ReferenceItemMapper>
        implements ReferenceItemService {

    public ReferenceItemImpl(
            ReferenceItemRepository repository,
            ReferenceItemMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<ReferenceItem> entityClass() {
        return ReferenceItem.class;
    }

    @Override
    protected ReferenceItem beforeCreateSave(ReferenceItem entity, ReferenceItemDto dto) {
        entity.setActive(true);
        return super.beforeCreateSave(entity, dto);
    }
}