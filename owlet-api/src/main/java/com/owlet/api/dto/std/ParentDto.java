package com.owlet.api.dto.std;

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
public class ParentDto extends BaseDto<UUID> {

    private String firstName;

    private String lastName;

    private String nationalCode;

    private ReferenceItemDto gender;

    private String mobile;

    private String email;

    private ReferenceItemDto occupation;

    private ReferenceItemDto education;

    private Boolean active;

    private AccountDto account;

    private String description;

}
