package com.owlet.api.service.base;

import java.io.Serializable;
import java.util.List;

public interface CrudService<
        ID extends Serializable,
        DTO,
        CREATE,
        UPDATE> {

    DTO get(ID id);

    List<DTO> getAll();

    DTO create(CREATE dto);

    DTO update(ID id, UPDATE dto);

    void delete(ID id);

}