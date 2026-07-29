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
public class StudentBadgeCreateRequest {

    @NotNull
    private EntityIdDto sessionStudent;

    @NotNull
    private EntityIdDto badgeType;

    private String title;

    private String description;

    @NotNull
    private LocalDate achievedAt;
}
