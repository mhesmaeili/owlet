package com.owlet.api.dto.edu;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentTemplateCreateRequest {


    @NotBlank
    private String title;

    @NotNull
    private EntityIdDto product;

    @NotNull
    private EntityIdDto sessionType;

    @NotNull
    private EntityIdDto assessmentType;

    private String description;

    @NotNull
    private Boolean active;

    private Integer versionNo;

}