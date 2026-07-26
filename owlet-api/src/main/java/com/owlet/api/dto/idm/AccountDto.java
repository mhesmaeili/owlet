package com.owlet.api.dto.idm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto extends BaseDto<UUID> {

    private String username;

    private String mobile;

    private String email;

    private String firstName;

    private String lastName;

    private String nationalCode;

    //private UUID avatarMediaId;

    private LocalDate birthDate;

    private Boolean active;

    private Boolean locked;

}