package com.owlet.api.dto.std;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
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
public class StudentHomeActivityCreateRequest {

    @NotNull
    private EntityIdDto trainingCourse;
    private Short levelOfEnthusiasm;
    private EntityIdDto independenceConstruction;
    private String parentResponse;
    private EntityIdDto student;

    private String description;


}
