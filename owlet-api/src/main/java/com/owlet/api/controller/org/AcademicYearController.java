package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.AcademicYearCreateRequest;
import com.owlet.api.dto.org.AcademicYearDto;
import com.owlet.api.service.org.AcademicYearService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "AcademicYearController")
@RestController
@RequestMapping("/api/org/academicYear")
public class AcademicYearController extends CrudController<
        UUID,
        AcademicYearDto,
        AcademicYearCreateRequest,
        AcademicYearCreateRequest> {

    public AcademicYearController(AcademicYearService service) {
        super(service);
    }

}