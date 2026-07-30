package com.owlet.api.mapper.base;

import com.owlet.api.domain.base.Attachment;
import com.owlet.api.dto.base.AttachmentCreateRequest;
import com.owlet.api.dto.base.AttachmentDto;
import org.mapstruct.Mapper;


@Mapper(config = BaseMapperConfig.class)
public interface AttachmentMapper extends CrudMapper<
        Attachment,
        AttachmentDto,
        AttachmentCreateRequest,
        AttachmentCreateRequest> {

}