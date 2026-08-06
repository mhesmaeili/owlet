package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.AdvancedCrudController;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.dto.ses.SessionStudentFilterDto;
import com.owlet.api.repository.specification.FilterNode;
import com.owlet.api.repository.specification.SearchOperation;
import com.owlet.api.service.ses.SessionStudentService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "SessionStudentController")
@RestController
@RequestMapping("/api/ses/sessionStudent")
public class SessionStudentController extends AdvancedCrudController<
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest,
        SessionStudentFilterDto> {

    public SessionStudentController(SessionStudentService service, SessionStudentService sessionStudentService) {
        super(service);
        this.sessionStudentService = sessionStudentService;
    }

    private final SessionStudentService sessionStudentService;

    @PutMapping("/updateAttendance")
    public ApiResponse<List<SessionStudentDto>> updateAttendance(@RequestParam UUID sessionId, @RequestParam boolean present, @RequestBody List<UUID> studentIds) {
        return ApiResponse.success(sessionStudentService.updateAttendance(sessionId, studentIds, present));
    }

    @PutMapping("/updateAttendanceUnique")
    public ApiResponse<SessionStudentDto> updateAttendance(@RequestParam UUID sessionId, @RequestParam boolean present, @RequestParam UUID studentId) {
        List<UUID> list = new ArrayList<>();
        list.add(studentId);
        return ApiResponse.success(sessionStudentService.updateAttendance(sessionId, list, present).getFirst());
    }

    @GetMapping("/{sessionId}/list")
    public ApiResponse<Page<SessionStudentDto>> getSessionStudents(
            @PathVariable UUID sessionId,
            @RequestParam(required = false) String keyword,
            Pageable pageable) {

        Page<SessionStudentDto> result = sessionStudentService.getStudentsBySession(sessionId, keyword, pageable);
        return ApiResponse.success(result);
    }

    @GetMapping("/{sessionId}/students")
    public ApiResponse<Page<SessionStudentDto>> getSessionStudents1(
            @PathVariable UUID sessionId,
            @RequestParam(required = false) String keyword,
            Pageable pageable) {

        FilterNode filterTree = FilterNode.and(

                FilterNode.condition("session.id", SearchOperation.EQUAL, sessionId),

                FilterNode.or(
                        FilterNode.condition("point", SearchOperation.GREATER_THAN, 10),
                        FilterNode.condition("present", SearchOperation.EQUAL, true)
                )
        );

        Page<SessionStudentDto> result = sessionStudentService.searchAdvanced(
                keyword,
                filterTree,
                pageable
        );

        return ApiResponse.success(result);
    }

}