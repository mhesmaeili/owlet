package com.owlet.api.dto.std;

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
public class StudentCreateRequest {

    private EntityIdDto account;

    private String studentNo;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String nationalCode;

    private LocalDate birthDate;

    private EntityIdDto gender;

    private EntityIdDto school;

    @NotNull
    private Boolean active;

    private String description;

}
