package com.owlet.api.controller.org;

import com.owlet.api.controller.GenericController;
import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "SchoolController", description = "")
@RestController
@RequestMapping("org/school")
public class SchoolController extends GenericController<School, UUID, SchoolDto> {
}
