package com.owlet.api.dto.ref;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceItemCreateRequest {
    @NotNull
    private String code;
    @NotNull
    private String title;
    private String titleFa;
    @NotNull
    private Integer sortOrder;
    private EntityIdDto referenceType;
}
