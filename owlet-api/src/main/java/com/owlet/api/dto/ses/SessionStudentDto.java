package com.owlet.api.dto.ses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.domain.ses.Session;
import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.std.StudentDto;
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
public class SessionStudentDto extends BaseDto<UUID> {

    private OffsetDateTime attendanceTime;

}
