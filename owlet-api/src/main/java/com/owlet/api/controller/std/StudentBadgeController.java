package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.StudentBadgeCreateRequest;
import com.owlet.api.dto.std.StudentBadgeDto;
import com.owlet.api.service.std.StudentBadgeService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "StudentBadgeController")
@RestController
@RequestMapping("/api/std/studentBadge")
public class StudentBadgeController extends CrudController<
        UUID,
        StudentBadgeDto,
        StudentBadgeCreateRequest,
        StudentBadgeCreateRequest> {

    public StudentBadgeController(StudentBadgeService service, StudentBadgeService studentBadgeService) {
        super(service);
        this.studentBadgeService = studentBadgeService;
    }

    private final StudentBadgeService studentBadgeService;

    @GetMapping("/bySessionAndBadge")
    public ApiResponse<List<StudentBadgeDto>> getBySessionAndBadge(
            @RequestParam UUID sessionId,
            @RequestParam UUID badgeTypeId) {
        return ApiResponse.success(studentBadgeService.getBySessionAndBadge(sessionId, badgeTypeId));
    }
}