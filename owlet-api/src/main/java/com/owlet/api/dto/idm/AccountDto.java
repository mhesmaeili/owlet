package com.owlet.api.dto.idm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDto extends BaseDto<UUID> {

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