package com.owlet.api.service.base.impl;

import com.owlet.api.domain.base.Qrcode;
import com.owlet.api.dto.base.QrcodeCreateRequest;
import com.owlet.api.dto.base.QrcodeDto;
import com.owlet.api.mapper.base.QrcodeMapper;
import com.owlet.api.repository.base.QrcodeRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.base.QrcodeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class QrcodeServiceImpl extends CrudServiceImpl<
        Qrcode,
        UUID,
        QrcodeDto,
        QrcodeCreateRequest,
        QrcodeCreateRequest,
        QrcodeRepository,
        QrcodeMapper>
        implements QrcodeService {

    public QrcodeServiceImpl(
            QrcodeRepository repository,
            QrcodeMapper mapper,
            AuditableService auditableService) {
        super(repository, mapper, auditableService);
    }

    @Override
    protected Class<Qrcode> entityClass() {
        return Qrcode.class;
    }
}