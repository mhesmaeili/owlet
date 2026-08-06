package com.owlet.api.controller.base;

import com.owlet.api.dto.base.BaseFilter;
import com.owlet.api.service.base.CrudService;
import com.owlet.common.response.ApiResponse;
import com.owlet.common.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public abstract class AdvancedCrudController<
        ID extends Serializable,
        DTO,
        CREATE,
        UPDATE,
        FILTER extends BaseFilter>
        extends CrudController<ID, DTO, CREATE, UPDATE> {

    public AdvancedCrudController(CrudService<ID, DTO, CREATE, UPDATE> service) {
        super(service);
    }

    @PostMapping("/search/advanced")
    public ApiResponse<PageResponse<DTO>> searchAdvanced(
            @RequestParam(required = false) String keyword,
            @RequestBody(required = false) FILTER filter,
            Pageable pageable) {

        Page<DTO> page = service.search(keyword, filter, pageable);

        return ApiResponse.success(PageResponse.of(page));
    }
}