package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.ClassroomCreateRequest;
import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.service.org.ClassroomService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "ClassroomController")
@RestController
@RequestMapping("/api/org/classroom")
public class ClassroomController extends CrudController<
        UUID,
        ClassroomDto,
        ClassroomCreateRequest,
        ClassroomCreateRequest> {

    public ClassroomController(ClassroomService service, ClassroomService classroomService) {
        super(service);
        this.classroomService = classroomService;
    }

    private final ClassroomService classroomService;

    @GetMapping("/findByTitle")
    public ApiResponse<List<ClassroomDto>> findByTitle(String title) {


        return ApiResponse.success(
                classroomService.findByTitle(title)
        );

    }
}