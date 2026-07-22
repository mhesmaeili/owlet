package com.owlet.api.service.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface CrudService<
        ID extends Serializable,
        DTO,
        CREATE,
        UPDATE> {

    DTO get(ID id);

    List<DTO> getAll();

    Page<DTO> getAll(Pageable pageable);

    DTO create(CREATE dto);

    DTO update(ID id, UPDATE dto);

    void delete(ID id);

    Page<DTO> search(
            String keyword,
            Pageable pageable);

    List<DTO> create(List<CREATE> dto);

    boolean exists(ID id);

    long count();
}