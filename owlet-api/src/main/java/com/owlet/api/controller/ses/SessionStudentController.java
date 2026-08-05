package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.service.ses.SessionStudentService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "SessionStudentController")
@RestController
@RequestMapping("/api/ses/sessionStudent")
public class SessionStudentController extends CrudController<
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest> {

    public SessionStudentController(SessionStudentService service, SessionStudentService sessionStudentService) {
        super(service);
        this.sessionStudentService = sessionStudentService;
    }

    private final SessionStudentService sessionStudentService;

    @PostMapping("/updateAttendance")
    public ApiResponse<List<SessionStudentDto>> updateAttendance(@RequestParam UUID sessionId, @RequestParam boolean present, @RequestBody List<UUID> studentIds) {
        return ApiResponse.success(sessionStudentService.updateAttendance(sessionId, studentIds, present));
    }

    @PostMapping("/updateAttendanceUnique")
    public ApiResponse<SessionStudentDto> updateAttendance(@RequestParam UUID sessionId, @RequestParam boolean present, @RequestParam UUID studentId) {
        List<UUID> list = new ArrayList<>();
        list.add(studentId);
        return ApiResponse.success(sessionStudentService.updateAttendance(sessionId, list, present).getFirst());
    }

}