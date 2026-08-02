package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentParentCreateRequest;
import com.owlet.api.dto.std.StudentParentDto;
import com.owlet.api.service.std.StudentParentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentParentController")
@RestController
@RequestMapping("/api/std/studentParent")
public class StudentParentController extends CrudController<
        UUID,
        StudentParentDto,
        StudentParentCreateRequest,
        StudentParentCreateRequest> {

    public StudentParentController(StudentParentService service) {
        super(service);
    }

}