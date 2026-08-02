package com.owlet.api.dto.std;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.ses.SessionStudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentAchievementDto extends BaseDto<UUID> {

    private SessionStudentDto sessionStudent;

    private String achievement;

    private LocalDate achievementDate;

    private ReferenceItemDto achievementType;

}
