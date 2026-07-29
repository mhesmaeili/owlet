package com.owlet.api.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDto extends BaseDto<UUID> {

    private String filename;

    private String mimeType;

    private String entityClass;

    private UUID entityId;

    private Long size;

    private String objectKey;

    private ReferenceItemDto category;

}
