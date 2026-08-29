package com.owlet.api.dto.std;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.dto.idm.AccountDto;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.ref.ReferenceItemDto;
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
public class StudentDto extends BaseDto<UUID> {


    private AccountDto account;


    private String studentNo;


    private String firstName;


    private String lastName;


    private String nationalCode;


    private LocalDate birthDate;


    private ReferenceItemDto gender;


    private SchoolDto school;

    private Boolean active;

    private String description;

    private String className;

}
