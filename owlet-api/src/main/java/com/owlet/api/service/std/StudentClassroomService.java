package com.owlet.api.service.std;


import com.owlet.api.dto.std.StudentClassroomCreateRequest;
import com.owlet.api.dto.std.StudentClassroomDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface StudentClassroomService extends CrudService<
        UUID,
        StudentClassroomDto,
        StudentClassroomCreateRequest,
        StudentClassroomCreateRequest> {
    List<StudentClassroomDto> findByClassroomId(UUID classroomId);
}