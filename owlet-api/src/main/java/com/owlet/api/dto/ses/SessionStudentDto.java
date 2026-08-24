package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.std.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionStudentDto extends BaseDto<UUID> {

    private SessionDto session;
    private StudentDto student;
    private OffsetDateTime attendanceTime;
    private Boolean present;
    private Boolean completed;
    private Integer point;
    private Boolean timeBase;
    private Integer number;
    private String shortDescription;
    private String aiResult;
    private String pointDescription;
    private String stateEvaluationSelected;
    private List<String> softSkillsSelected;
}
