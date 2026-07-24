package com.owlet.api.controller.org;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.org.BranchCreateRequest;
import com.owlet.api.dto.org.BranchDto;
import com.owlet.api.service.org.BranchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "BranchController")
@RestController
@RequestMapping("/api/org/Branch")
public class BranchController extends CrudController<
        UUID,
        BranchDto,
        BranchCreateRequest,
        BranchCreateRequest> {

    public BranchController(BranchService service) {
        super(service);
    }

}