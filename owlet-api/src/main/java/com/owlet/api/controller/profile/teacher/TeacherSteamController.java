package com.owlet.api.controller.profile.teacher;

import com.owlet.api.dto.org.ClassroomDto;
import com.owlet.api.dto.profile.school.ProfileTeacherClassroomDto;
import com.owlet.api.dto.profile.school.TeacherSchoolDto;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.service.org.ClassroomService;
import com.owlet.api.service.org.SchoolService;
import com.owlet.api.service.ses.SessionStudentService;
import com.owlet.api.service.ses.TrainingCourseService;
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
@RequestMapping("/api/teacher/steam")
@AllArgsConstructor
public class TeacherSteamController {

    private final SchoolService schoolService;
    private final ClassroomService classroomService;
    private final TrainingCourseService trainingCourseService;
    private final SessionStudentService sessionStudentService;

    @GetMapping("/schoolList")
    public ApiResponse<List<TeacherSchoolDto>> school() {
        return ApiResponse.success(schoolService.teacherSteamWorkWithSchool());
    }

    @GetMapping("/classroomList")
    public ApiResponse<List<ProfileTeacherClassroomDto>> classroom(@RequestParam UUID schoolId) {
        return ApiResponse.success(classroomService.teacherSteamClassroom(schoolId));
    }

    @GetMapping("/sessionList")
    public ApiResponse<List<SessionDto>> session(@RequestParam UUID classroomId) {
        return ApiResponse.success(trainingCourseService.teacherSteamCourse(classroomId));
    }

    @GetMapping("/sessionStudentList")
    public ApiResponse<List<SessionStudentDto>> sessionStudent(@RequestParam UUID sessionId) {
        return ApiResponse.success(sessionStudentService.studentListBySessionId(sessionId));
    }

}