package com.owlet.api.mapper.ref;

import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ReferenceItemMapper extends CrudMapper<
        ReferenceItem,
        ReferenceItemDto,
        ReferenceItemDto,
        ReferenceItemDto> {
}