package com.owlet.api.controller.base;

import com.owlet.api.annotation.PublicEndpoint;
import com.owlet.api.dto.base.ContactMessageCreateRequest;
import com.owlet.api.dto.base.ContactMessageDto;
import com.owlet.api.service.base.ContactMessageService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "ContactMessageController")
@RestController
@RequestMapping("/api/base/contactMessage")
public class ContactMessageController extends CrudController<
        UUID,
        ContactMessageDto,
        ContactMessageCreateRequest,
        ContactMessageCreateRequest> {

    private final ContactMessageService ContactMessageService;

    public ContactMessageController(ContactMessageService service, ContactMessageService ContactMessageService) {
        super(service);
        this.ContactMessageService = ContactMessageService;
    }

    @PublicEndpoint
    @Override
    @PostMapping
    public ApiResponse<ContactMessageDto> create(@Valid @RequestBody ContactMessageCreateRequest request) {
        return super.create(request);
    }

}