package com.owlet.api.dto.idm;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountCreateRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String mobile;

    private String email;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String nationalCode;

    private UUID avatarMediaId;

    private UUID genderId;

    private LocalDate birthDate;

    private List<UUID> roleIds;

}