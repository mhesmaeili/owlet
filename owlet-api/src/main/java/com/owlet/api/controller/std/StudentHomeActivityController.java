package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentHomeActivityCreateRequest;
import com.owlet.api.dto.std.StudentHomeActivityDto;
import com.owlet.api.service.std.StudentHomeActivityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentHomeActivityController")
@RestController
@RequestMapping("/api/std/studentHomeActivity")
public class StudentHomeActivityController extends CrudController<
        UUID,
        StudentHomeActivityDto,
        StudentHomeActivityCreateRequest,
        StudentHomeActivityCreateRequest> {

    public StudentHomeActivityController(StudentHomeActivityService service) {
        super(service);
    }

}