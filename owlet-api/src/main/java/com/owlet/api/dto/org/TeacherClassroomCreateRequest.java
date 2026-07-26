package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherClassroomCreateRequest {

    @NotNull
    private EntityIdDto teacherAccount;
    @NotNull
    private EntityIdDto classroom;
    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;
    @NotNull
    private Boolean active;
    private String description;

}
