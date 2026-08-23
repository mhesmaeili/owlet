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
public class SessionCreateRequest {

    @NotNull
    private EntityIdDto trainingCourse;
    @NotNull
    private String title;
    private OffsetDateTime startTime;
    private String code;
    private String teacherSummary;
    private Boolean finalized;
    private OffsetDateTime finalizedAt;
    @NotNull
    private EntityIdDto sessionType;

}
