package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentClassroomCreateRequest;
import com.owlet.api.dto.std.StudentClassroomDto;
import com.owlet.api.service.std.StudentClassroomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentClassroomController")
@RestController
@RequestMapping("/api/std/studentClassroom")
public class StudentClassroomController extends CrudController<
        UUID,
        StudentClassroomDto,
        StudentClassroomCreateRequest,
        StudentClassroomCreateRequest> {

    public StudentClassroomController(StudentClassroomService service) {
        super(service);
    }

}