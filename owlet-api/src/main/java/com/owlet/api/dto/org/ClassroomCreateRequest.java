package com.owlet.api.dto.org;

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
public class ClassroomCreateRequest {

    @NotBlank
    private String title;

    private String code;

    @NotNull
    private EntityIdDto school;

    @NotNull
    private EntityIdDto grade;

    @NotNull
    private EntityIdDto academicYears;

    private Integer capacity;

    private String description;

}
