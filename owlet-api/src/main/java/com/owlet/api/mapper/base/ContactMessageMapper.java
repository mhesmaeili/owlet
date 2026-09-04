package com.owlet.api.mapper.base;

import com.owlet.api.domain.base.ContactMessage;
import com.owlet.api.dto.base.ContactMessageCreateRequest;
import com.owlet.api.dto.base.ContactMessageDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ContactMessageMapper extends CrudMapper<ContactMessage, ContactMessageDto, ContactMessageCreateRequest, ContactMessageCreateRequest> {
}