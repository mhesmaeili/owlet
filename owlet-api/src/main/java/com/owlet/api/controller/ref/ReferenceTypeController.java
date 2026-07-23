package com.owlet.api.controller.ref;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ref.ReferenceTypeDto;
import com.owlet.api.service.ref.ReferenceTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "ReferenceTypeController")
@RestController
@RequestMapping("/api/ref/referenceType")
public class ReferenceTypeController extends CrudController<
        UUID,
        ReferenceTypeDto,
        ReferenceTypeDto,
        ReferenceTypeDto> {

    public ReferenceTypeController(ReferenceTypeService service) {
        super(service);
    }

}