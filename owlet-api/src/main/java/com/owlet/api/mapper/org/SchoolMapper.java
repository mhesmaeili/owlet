package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SchoolMapper extends CrudMapper<
        School,
        SchoolDto,
        SchoolDto,
        SchoolDto> {
}