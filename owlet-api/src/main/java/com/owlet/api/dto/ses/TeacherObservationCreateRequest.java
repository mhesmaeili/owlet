package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.ref.ReferenceItemDto;
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

    private EntityIdDto teacherAccount;

    private String observation;

    private EntityIdDto student;

    private EntityIdDto sessionStudent;

    private EntityIdDto positive;
    private EntityIdDto negative;
    private EntityIdDto neutral;

}
