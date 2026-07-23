package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.SchoolCreateRequest;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.org.SchoolUpdateRequest;
import com.owlet.api.service.org.SchoolService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "SchoolController")
@RestController
@RequestMapping("/api/org/school")
public class SchoolController extends CrudController<
        UUID,
        SchoolDto,
        SchoolCreateRequest,
        SchoolUpdateRequest> {

    public SchoolController(SchoolService service) {
        super(service);
    }

}