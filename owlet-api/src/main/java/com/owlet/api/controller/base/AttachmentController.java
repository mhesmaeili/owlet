package com.owlet.api.controller.base;

import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import com.owlet.api.service.base.AttachmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "AttachmentController")
@RestController
@RequestMapping("/api/base/attachment")
public class AttachmentController extends CrudController<
        UUID,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {

    public AttachmentController(AttachmentService service) {
        super(service);
    }

}