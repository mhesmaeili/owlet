package com.owlet.api.mapper.base;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<
        ENTITY,
        DTO> {

    DTO toDto(ENTITY entity);

    List<DTO> toDto(List<ENTITY> entities);
}