package com.owlet.api.dto.std;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.ses.TrainingCourseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentHomeActivityDto extends BaseDto<UUID> {

    private TrainingCourseDto trainingCourse;
    private Short levelOfEnthusiasm;
    private ReferenceItemDto independenceConstruction;
    private String parentResponse;

    private String description;

}
