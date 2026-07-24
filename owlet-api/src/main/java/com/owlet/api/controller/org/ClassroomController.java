package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.service.org.ClassroomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "ClassroomController")
@RestController
@RequestMapping("/api/org/classroom")
public class ClassroomController extends CrudController<
        UUID,
        ClassroomDto,
        ClassroomCreateRequest,
        ClassroomCreateRequest> {

    public ClassroomController(ClassroomService service) {
        super(service);
    }

}