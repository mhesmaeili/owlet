package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotBlank;
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
public class TeacherObservationCreateRequest {

    @NotBlank
    private EntityIdDto teacherAccount;

    @NotBlank
    private String observation;

    private EntityIdDto student;

    private EntityIdDto sessionStudent;

}
