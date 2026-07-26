package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentCreateRequest;
import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.service.std.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentController")
@RestController
@RequestMapping("/api/std/student")
public class StudentController extends CrudController<
        UUID,
        StudentDto,
        StudentCreateRequest,
        StudentCreateRequest> {

    public StudentController(StudentService service) {
        super(service);
    }

}