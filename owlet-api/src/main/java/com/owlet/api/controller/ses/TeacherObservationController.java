package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.TeacherObservationCreateRequest;
import com.owlet.api.dto.ses.TeacherObservationDto;
import com.owlet.api.service.ses.TeacherObservationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "TeacherObservationController")
@RestController
@RequestMapping("/api/ses/teacherObservation")
public class TeacherObservationController extends CrudController<
        UUID,
        TeacherObservationDto,
        TeacherObservationCreateRequest,
        TeacherObservationCreateRequest> {

    public TeacherObservationController(TeacherObservationService service) {
        super(service);
    }

}