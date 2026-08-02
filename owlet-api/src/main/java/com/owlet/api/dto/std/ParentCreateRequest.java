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
public class ParentCreateRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String nationalCode;

    private EntityIdDto gender;

    private String mobile;

    private String email;

    private EntityIdDto occupation;

    private EntityIdDto education;

    private Boolean active;

    private EntityIdDto account;

    private String description;

}
