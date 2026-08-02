package com.owlet.api.controller.std;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.std.ParentCreateRequest;
import com.owlet.api.dto.std.ParentDto;
import com.owlet.api.service.std.ParentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "ParentController")
@RestController
@RequestMapping("/api/std/parent")
public class ParentController extends CrudController<
        UUID,
        ParentDto,
        ParentCreateRequest,
        ParentCreateRequest> {

    public ParentController(ParentService service) {
        super(service);
    }

}