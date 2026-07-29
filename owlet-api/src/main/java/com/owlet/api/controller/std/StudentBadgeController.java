package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentBadgeCreateRequest;
import com.owlet.api.dto.std.StudentBadgeDto;
import com.owlet.api.service.std.StudentBadgeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentBadgeController")
@RestController
@RequestMapping("/api/std/studentBadge")
public class StudentBadgeController extends CrudController<
        UUID,
        StudentBadgeDto,
        StudentBadgeCreateRequest,
        StudentBadgeCreateRequest> {

    public StudentBadgeController(StudentBadgeService service) {
        super(service);
    }

}