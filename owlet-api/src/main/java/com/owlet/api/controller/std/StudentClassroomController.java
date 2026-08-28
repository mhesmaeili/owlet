package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentClassroomBatchRequest;
import com.owlet.api.dto.std.StudentClassroomCreateRequest;
import com.owlet.api.dto.std.StudentClassroomDto;
import com.owlet.api.service.std.StudentClassroomService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "StudentClassroomController")
@RestController
@RequestMapping("/api/std/studentClassroom")
public class StudentClassroomController extends CrudController<
        UUID,
        StudentClassroomDto,
        StudentClassroomCreateRequest,
        StudentClassroomCreateRequest> {

    public StudentClassroomController(StudentClassroomService service, StudentClassroomService studentClassroomService) {
        super(service);
        this.studentClassroomService = studentClassroomService;
    }
    private final StudentClassroomService studentClassroomService;

    @PostMapping("/batch-status")
    public ApiResponse<Void> updateBatchStatus(@Valid @RequestBody StudentClassroomBatchRequest request) {

        studentClassroomService.updateBatchStatus(request.getIds(), request.getActive());

        return ApiResponse.success(null);
    }
}