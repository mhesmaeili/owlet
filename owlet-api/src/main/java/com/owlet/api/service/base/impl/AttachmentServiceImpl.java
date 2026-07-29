package com.owlet.api.service.base.impl;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.mapper.base.AttachmentMapper;
import com.owlet.api.repository.base.AttachmentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.AttachmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AttachmentServiceImpl extends CrudServiceImpl<
        Attachment,
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest,
        AttachmentRepository,
        AttachmentMapper>
        implements AttachmentService {

    public AttachmentServiceImpl(
            AttachmentRepository repository,
            AttachmentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }

    @Override
    protected Class<Attachment> entityClass() {
        return Attachment.class;
    }
}