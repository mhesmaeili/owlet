package com.owlet.api.controller.base;

import com.owlet.api.service.base.CrudService;
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
    public DTO get(@PathVariable ID id) {

        return service.get(id);
    }

    @GetMapping
    public List<DTO> getAll() {

        return service.getAll();
    }

    @GetMapping("/page")
    public Page<DTO> getAll(Pageable pageable){

        return service.getAll(pageable);

    }

    @PostMapping
    public DTO create(
            @RequestBody CREATE dto) {

        return service.create(dto);
    }

    @PutMapping("/{id}")
    public DTO update(
            @PathVariable ID id,
            @RequestBody UPDATE dto) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable ID id) {

        service.delete(id);
    }

    @PostMapping("/batch")
    public List<DTO> create(
            @RequestBody List<CREATE> dto){

        return service.create(dto);

    }

}