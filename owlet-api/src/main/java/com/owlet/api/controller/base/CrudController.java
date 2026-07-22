package com.owlet.api.controller.base;

import com.owlet.api.service.base.CrudService;
import com.owlet.common.response.ApiResponse;
import com.owlet.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
public abstract class CrudController<
        ID extends Serializable,
        DTO,
        CREATE,
        UPDATE> {

    protected final CrudService<
            ID,
            DTO,
            CREATE,
            UPDATE> service;

    @GetMapping("/{id}")
    public ApiResponse<DTO> get(
            @PathVariable ID id) {


        return ApiResponse.success(
                service.get(id)
        );

    }

    @GetMapping
    public ApiResponse<List<DTO>> getAll() {


        return ApiResponse.success(
                service.getAll()
        );

    }

    @GetMapping("/page")
    public ApiResponse<PageResponse<DTO>> getPage(
            Pageable pageable) {


        Page<DTO> page =
                service.getAll(pageable);


        return ApiResponse.success(
                PageResponse.of(page)
        );

    }

    @GetMapping("/search")
    public ApiResponse<PageResponse<DTO>> search(
            @RequestParam(required = false)
            String keyword,

            Pageable pageable) {


        Page<DTO> page =
                service.search(
                        keyword,
                        pageable
                );


        return ApiResponse.success(
                PageResponse.of(page)
        );

    }

    @PostMapping
    public ApiResponse<DTO> create(
            @RequestBody CREATE dto) {


        return ApiResponse.success(
                service.create(dto)
        );

    }

    @PutMapping("/{id}")
    public ApiResponse<DTO> update(
            @PathVariable ID id,
            @RequestBody UPDATE dto) {


        return ApiResponse.success(
                service.update(id,dto)
        );

    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(
            @PathVariable ID id) {


        service.delete(id);


        return ApiResponse.success(null);

    }

    @PostMapping("/batch")
    public ApiResponse<List<DTO>> create(
            @RequestBody List<CREATE> dto) {

        return ApiResponse.success(service.create(dto));

    }

}