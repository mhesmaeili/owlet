package com.owlet.api.dto.ref;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenceItemDto extends BaseDto<UUID> {
    private String code;
    private String title;
    private String titleFa;
    ReferenceTypeDto referenceType;
}
