package com.owlet.api.controller.ses;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.service.ses.SessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "SessionController")
@RestController
@RequestMapping("/api/ses/session")
public class SessionController extends CrudController<
        UUID,
        SessionDto,
        SessionCreateRequest,
        SessionCreateRequest> {

    public SessionController(SessionService service) {
        super(service);
    }

}