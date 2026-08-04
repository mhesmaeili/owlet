package com.owlet.api.service.org;


import com.owlet.api.domain.org.Classroom;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface ClassroomService extends CrudService<
        UUID,
        ClassroomDto,
        ClassroomCreateRequest,
        ClassroomCreateRequest> {

    List<ClassroomDto> findByTitle(String title);
}