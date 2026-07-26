package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.TeacherClassroomCreateRequest;
import com.owlet.api.dto.org.TeacherClassroomDto;
import com.owlet.api.service.org.TeacherClassroomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "TeacherClassroomController")
@RestController
@RequestMapping("/api/org/teacherClassroom")
public class TeacherClassroomController extends CrudController<
        UUID,
        TeacherClassroomDto,
        TeacherClassroomCreateRequest,
        TeacherClassroomCreateRequest> {

    public TeacherClassroomController(TeacherClassroomService service) {
        super(service);
    }

}