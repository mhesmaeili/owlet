package com.owlet.api.dto.idm;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccountDto {

    private UUID id;

    private String username;

    private String mobile;

    private String email;

    private String firstName;

    private String lastName;

    private String nationalCode;

    private UUID avatarMediaId;

    private LocalDate birthDate;

    private Boolean active;

    private Boolean locked;

}