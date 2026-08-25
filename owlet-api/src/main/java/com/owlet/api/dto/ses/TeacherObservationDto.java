package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
import com.owlet.api.dto.std.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherObservationDto extends BaseDto<UUID> {


    private AccountDto teacherAccount;

    private String observation;

    private StudentDto student;

    private SessionStudentDto sessionStudent;

    private ReferenceItemDto positive;
    private ReferenceItemDto negative;
    private ReferenceItemDto neutral;

}
