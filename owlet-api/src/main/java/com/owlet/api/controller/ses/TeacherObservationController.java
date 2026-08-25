package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.TeacherObservationCreateRequest;
import com.owlet.api.dto.ses.TeacherObservationDto;
import com.owlet.api.service.ses.TeacherObservationService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "TeacherObservationController")
@RestController
@RequestMapping("/api/ses/teacherObservation")
public class TeacherObservationController extends CrudController<
        UUID,
        TeacherObservationDto,
        TeacherObservationCreateRequest,
        TeacherObservationCreateRequest> {

    public TeacherObservationController(TeacherObservationService service, TeacherObservationService teacherObservationService) {
        super(service);
        this.teacherObservationService = teacherObservationService;
    }

    private final TeacherObservationService teacherObservationService;

    @GetMapping("/bySession")
    public ApiResponse<List<TeacherObservationDto>> getBySession(@RequestParam UUID sessionId) {
        return ApiResponse.success(teacherObservationService.getBySessionId(sessionId));
    }
}