package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionDto extends BaseDto<UUID> {


    private TrainingCourseDto trainingCourse;
    private String title;
    private OffsetDateTime startTime;
    private String code;
    private String teacherSummary;
    private Boolean finalized;
    private OffsetDateTime finalizedAt;
    private ReferenceItemDto sessionType;
    private Boolean attendanceSubmitted;
}
