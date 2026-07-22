package com.owlet.api.mapper.base;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface CrudMapper<
        ENTITY,
        DTO,
        CREATE,
        UPDATE> {

    DTO toDto(ENTITY entity);

    List<DTO> toDto(List<ENTITY> entities);

    ENTITY toEntity(CREATE dto);

    void update(
            UPDATE dto,
            @MappingTarget ENTITY entity);

}