package com.owlet.api.dto.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotNull;
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
public class AttachmentReferenceCreateRequest extends BaseDto<UUID> {

    @NotNull
    private String entityClass;

    @NotNull
    private UUID entityId;

    private EntityIdDto category;

    @NotNull
    private EntityIdDto attachment;

}
