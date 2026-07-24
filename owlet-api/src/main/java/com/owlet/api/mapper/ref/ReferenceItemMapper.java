package com.owlet.api.mapper.ref;

import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.dto.ref.ReferenceItemCreateRequest;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface ReferenceItemMapper extends CrudMapper<
        ReferenceItem,
        ReferenceItemDto,
        ReferenceItemCreateRequest,
        ReferenceItemCreateRequest> {

    @Mapping(target = "referenceType",
            source = "referenceType",
            qualifiedByName = "toReference")
    ReferenceItem toEntity(ReferenceItemCreateRequest dto);

    @Mapping(target = "referenceType",
            source = "referenceType",
            qualifiedByName = "toReference")
    @Override
    void update(ReferenceItemCreateRequest referenceItemCreateRequest, @MappingTarget ReferenceItem referenceItem);
}