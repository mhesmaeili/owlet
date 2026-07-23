package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolCreateRequest;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.org.SchoolUpdateRequest;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface SchoolMapper extends CrudMapper<
        School,
        SchoolDto,
        SchoolCreateRequest,
        SchoolUpdateRequest> {
}