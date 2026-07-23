package com.owlet.api.controller.ref;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.service.ref.ReferenceItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "ReferenceItemController")
@RestController
@RequestMapping("/api/ref/referenceItem")
public class ReferenceItemController extends CrudController<
        UUID,
        ReferenceItemDto,
        ReferenceItemDto,
        ReferenceItemDto> {

    public ReferenceItemController(ReferenceItemService service) {
        super(service);
    }

}