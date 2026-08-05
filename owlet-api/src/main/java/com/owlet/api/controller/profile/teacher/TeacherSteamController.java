package com.owlet.api.controller.profile.teacher;

import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.service.org.SchoolService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "TeacherSteamController")
@RestController
@RequestMapping("/api/teacher/steam")
@AllArgsConstructor
public class TeacherSteamController {

    private final SchoolService schoolService;

    @GetMapping("/schoolList")
    public ApiResponse<List<SchoolDto>> school() {
        return ApiResponse.success(schoolService.teacherSteamWorkWithSchool());
    }

}