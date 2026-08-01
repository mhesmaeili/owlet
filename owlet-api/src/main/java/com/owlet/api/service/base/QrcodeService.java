package com.owlet.api.service.base;

import com.owlet.api.dto.base.QrcodeCreateRequest;
import com.owlet.api.dto.base.QrcodeDto;
import java.util.UUID;

public interface QrcodeService extends CrudService<
        UUID,
        QrcodeDto,
        QrcodeCreateRequest,
        QrcodeCreateRequest> {

}