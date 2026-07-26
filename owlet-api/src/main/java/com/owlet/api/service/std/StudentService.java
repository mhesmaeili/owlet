package com.owlet.api.service.std;



import com.owlet.api.dto.std.StudentCreateRequest;
import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface StudentService extends CrudService<
        UUID,
        StudentDto,
        StudentCreateRequest,
        StudentCreateRequest> {
}