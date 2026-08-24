package com.owlet.api.controller.ref;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.ref.ReferenceItemCreateRequest;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.service.ref.ReferenceItemService;
import com.owlet.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "ReferenceItemController")
@RestController
@RequestMapping("/api/ref/referenceItem")
public class ReferenceItemController extends CrudController<
        UUID,
        ReferenceItemDto,
        ReferenceItemCreateRequest,
        ReferenceItemCreateRequest> {

    public ReferenceItemController(ReferenceItemService service, ReferenceItemService referenceItemService) {
        super(service);
        this.referenceItemService = referenceItemService;
    }

    private final ReferenceItemService referenceItemService;

    @GetMapping("/byTypeCode")
    public ApiResponse<List<ReferenceItemDto>> session(@RequestParam String typeCode) {
        return ApiResponse.success(referenceItemService.loadByTypeCode(typeCode));
    }

}