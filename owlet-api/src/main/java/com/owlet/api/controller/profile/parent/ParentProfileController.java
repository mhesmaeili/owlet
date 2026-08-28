package com.owlet.api.controller.profile.parent;

import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.service.std.StudentParentService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "TeacherSteamController")
@RestController
@RequestMapping("/api/parent/profile")
@AllArgsConstructor
public class ParentProfileController {

    private final StudentParentService studentParentService;

    @GetMapping("/studentInfo")
    public ApiResponse<List<StudentDto>> studentInfo() {
        return ApiResponse.success(studentParentService.getStudent());
    }

}
