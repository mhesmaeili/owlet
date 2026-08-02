package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionStudentCreateRequest {

    @NotNull
    private EntityIdDto session;
    @NotNull
    private EntityIdDto student;
    private OffsetDateTime attendanceTime;
    @NotNull
    private Boolean present;
    private Boolean completed;

    private Integer point;
    private Boolean timeBase;
    private Integer number;
    private EntityIdDto stateEvaluation;
    private String shortDescription;
    private String aiResult;

}
