package com.owlet.api.dto.org;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
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
public class ClassroomDto extends BaseDto<UUID> {

    private String title;

    private String code;

    private SchoolDto school;

    private ReferenceItemDto grade;

    private AcademicYearDto academicYears;

    private AccountDto teacherAccount;

    private Integer capacity;

    private Boolean active;

    private String description;

}
