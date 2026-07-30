package com.owlet.api.mapper.base;

import com.owlet.api.domain.base.AttachmentReference;
import com.owlet.api.dto.base.AttachmentReferenceCreateRequest;
import com.owlet.api.dto.base.AttachmentReferenceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface AttachmentReferenceMapper extends CrudMapper<
        AttachmentReference,
        AttachmentReferenceDto,
        AttachmentReferenceCreateRequest,
        AttachmentReferenceCreateRequest> {

    @Mapping(target = "category",
            source = "category",
            qualifiedByName = "toReference")
    @Mapping(target = "attachment",
            source = "attachment",
            qualifiedByName = "toReference")
    @Override
    AttachmentReference toEntity(AttachmentReferenceCreateRequest attachmentReferenceCreateRequest);

    @Mapping(target = "category",
            source = "category",
            qualifiedByName = "toReference")
    @Mapping(target = "attachment",
            source = "attachment",
            qualifiedByName = "toReference")
    @Override
    void update(AttachmentReferenceCreateRequest attachmentReferenceCreateRequest,@MappingTarget AttachmentReference attachmentReference);
}

