package com.owlet.api.service.base.impl;

import com.owlet.api.domain.base.ContactMessage;
import com.owlet.api.dto.base.ContactMessageCreateRequest;
import com.owlet.api.dto.base.ContactMessageDto;
import com.owlet.api.mapper.base.ContactMessageMapper;
import com.owlet.api.repository.base.ContactMessageRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.ContactMessageService;
import com.owlet.api.service.base.CrudServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ContactMessageServiceImpl extends CrudServiceImpl<
        ContactMessage,
        UUID,
        ContactMessageDto,
        ContactMessageCreateRequest,
        ContactMessageCreateRequest,
        ContactMessageRepository,
        ContactMessageMapper>
        implements ContactMessageService {

    public ContactMessageServiceImpl(
            ContactMessageRepository repository,
            ContactMessageMapper mapper,
            AuditableService auditableService) {
        super(repository, mapper, auditableService);
    }

    @Override
    protected Class<ContactMessage> entityClass() {
        return ContactMessage.class;
    }
}