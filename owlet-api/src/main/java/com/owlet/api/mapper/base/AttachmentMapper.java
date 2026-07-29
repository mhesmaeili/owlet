package com.owlet.api.mapper.base;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface AttachmentMapper extends CrudMapper<
        Attachment,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {

    @Mapping(target = "category",
            source = "category",
            qualifiedByName = "toReference")
    @Override
    Attachment toEntity(AttachmentCreateRequest attachmentCreateRequest);

    @Mapping(target = "category",
            source = "category",
            qualifiedByName = "toReference")
    @Override
    void update(AttachmentCreateRequest attachmentCreateRequest,@MappingTarget Attachment attachment);
}