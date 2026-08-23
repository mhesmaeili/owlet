package com.owlet.api.dto.ref;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceItemDto extends BaseDto<UUID> {

    private String code;
    @NotBlank
    private String title;
    @NotBlank
    private String titleFa;
    @NotBlank
    private Integer sortOrder;
    @NotBlank
    ReferenceTypeDto referenceType;

    private String icon;
}
