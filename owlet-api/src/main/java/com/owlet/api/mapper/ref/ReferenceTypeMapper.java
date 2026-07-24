package com.owlet.api.mapper.ref;

import com.owlet.api.domain.org.Branch;
import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.domain.ref.ReferenceType;
import com.owlet.api.dto.org.BranchCreateRequest;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.ref.ReferenceTypeCreateRequest;
import com.owlet.api.dto.ref.ReferenceTypeDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = BaseMapperConfig.class)
public interface ReferenceTypeMapper extends CrudMapper<
        ReferenceType,
        ReferenceTypeDto,
        ReferenceTypeCreateRequest,
        ReferenceTypeCreateRequest> {
}