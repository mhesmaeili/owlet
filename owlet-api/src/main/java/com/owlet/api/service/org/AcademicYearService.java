package com.owlet.api.service.org;


import com.owlet.api.dto.org.AcademicYearCreateRequest;
import com.owlet.api.dto.org.AcademicYearDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface AcademicYearService extends CrudService<
        UUID,
        AcademicYearDto,
        AcademicYearCreateRequest,
        AcademicYearCreateRequest> {
}