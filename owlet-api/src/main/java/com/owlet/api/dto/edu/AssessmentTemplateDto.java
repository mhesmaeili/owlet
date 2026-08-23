package com.owlet.api.dto.edu;

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
public class AssessmentTemplateDto extends BaseDto<UUID> {


    private String title;

    private String description;

    private Boolean active;

    private Integer versionNo;

    private ReferenceItemDto assessmentType;

}