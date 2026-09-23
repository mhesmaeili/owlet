package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.base.AttachmentUrlDto;
import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.service.ses.SessionService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "SessionController")
@RestController
@RequestMapping("/api/ses/session")
public class SessionController extends CrudController<
        UUID,
        SessionDto,
        SessionCreateRequest,
        SessionCreateRequest> {

    public SessionController(SessionService service, SessionService sessionService) {
        super(service);
        this.sessionService = sessionService;
    }


    private final SessionService sessionService;

    @PutMapping("/{id}/finalize")
    public ApiResponse<SessionDto> finalizeSession(@PathVariable UUID id) {
        SessionDto result = sessionService.finalizeSession(id);
        return ApiResponse.success("Session finalized successfully", result);
    }

    @PutMapping("/{id}/attendanceSubmitted")
    public ApiResponse<SessionDto> attendanceSubmittedSession(@PathVariable UUID id) {
        SessionDto result = sessionService.attendanceSubmitted(id);
        return ApiResponse.success("Session finalized successfully", result);
    }

    @GetMapping("/galleryBySessionId")
    public ApiResponse<List<AttachmentUrlDto>> galleryBySessionId(
            @RequestParam UUID sessionId) {
        return ApiResponse.success(sessionService.galleryBySessionId(sessionId));
    }
}