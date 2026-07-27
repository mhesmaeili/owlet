package com.owlet.api.service.std;


import com.owlet.api.dto.std.StudentAttendanceAbsenceCreateRequest;
import com.owlet.api.dto.std.StudentAttendanceAbsenceDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface StudentAttendanceAbsenceService extends CrudService<
        UUID,
        StudentAttendanceAbsenceDto,
        StudentAttendanceAbsenceCreateRequest,
        StudentAttendanceAbsenceCreateRequest> {
}