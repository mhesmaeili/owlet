package com.owlet.api.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto<PK extends Serializable> implements Serializable {
    private PK id;
    private OffsetDateTime createdAt;
    private UUID createdBy;
    private OffsetDateTime updatedAt;
    private UUID updatedBy;
    private Boolean deleted;
    private OffsetDateTime deletedAt;
    private UUID deletedBy;
    private Long version;
}
