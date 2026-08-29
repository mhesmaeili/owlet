package com.owlet.api.controller.profile.parent;

import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.dto.std.StudentDto;
import com.owlet.api.service.ses.TrainingCourseService;
import com.owlet.api.service.std.StudentParentService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "TeacherSteamController")
@RestController
@RequestMapping("/api/parent/profile")
@AllArgsConstructor
public class ParentProfileController {

    private final StudentParentService studentParentService;
    private final TrainingCourseService trainingCourseService;

    @GetMapping("/studentInfo")
    public ApiResponse<List<StudentDto>> studentInfo() {
        return ApiResponse.success(studentParentService.getStudent());
    }


    @GetMapping("/trainingInfo")
    public ApiResponse<List<TrainingCourseDto>> trainingInfo(@RequestParam UUID studentId) {
        return ApiResponse.success(trainingCourseService.getByStudentId(studentId));
    }

    @GetMapping("/studentGallery")
    public ApiResponse<List<AttachmentUrlDto>> studentGallery(@RequestParam UUID studentId) {
        return ApiResponse.success(trainingCourseService.getStudentGallery(studentId));
    }
}
