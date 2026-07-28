package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.service.ses.SessionStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "SessionStudentController")
@RestController
@RequestMapping("/api/ses/sessionStudent")
public class SessionStudentController extends CrudController<
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest> {

    public SessionStudentController(SessionStudentService service) {
        super(service);
    }

}