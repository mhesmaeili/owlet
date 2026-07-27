package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentAttendanceAbsenceCreateRequest;
import com.owlet.api.dto.std.StudentAttendanceAbsenceDto;
import com.owlet.api.service.std.StudentAttendanceAbsenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentAttendanceAbsenceController")
@RestController
@RequestMapping("/api/std/studentAttendanceAbsence")
public class StudentAttendanceAbsenceController extends CrudController<
        UUID,
        StudentAttendanceAbsenceDto,
        StudentAttendanceAbsenceCreateRequest,
        StudentAttendanceAbsenceCreateRequest> {

    public StudentAttendanceAbsenceController(StudentAttendanceAbsenceService service) {
        super(service);
    }

}