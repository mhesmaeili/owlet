package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.SchoolMemberCreateRequest;
import com.owlet.api.dto.org.SchoolMemberDto;
import com.owlet.api.service.org.SchoolMemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "SchoolMemberController")
@RestController
@RequestMapping("/api/org/schoolMember")
public class SchoolMemberController extends CrudController<
        UUID,
        SchoolMemberDto,
        SchoolMemberCreateRequest,
        SchoolMemberCreateRequest> {

    public SchoolMemberController(SchoolMemberService service) {
        super(service);
    }

}