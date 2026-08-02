package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.service.ses.TrainingCourseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "TrainingCourseController")
@RestController
@RequestMapping("/api/ses/trainingCourse")
public class TrainingCourseController extends CrudController<
        UUID,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest> {

    public TrainingCourseController(TrainingCourseService service) {
        super(service);
    }

}