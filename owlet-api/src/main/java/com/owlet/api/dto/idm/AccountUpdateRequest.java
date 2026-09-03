package com.owlet.api.dto.idm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.service.base.helper.EntityIdDto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUpdateRequest {

    private String password;

    private String mobile;

    private String email;

    private String firstName;

    private String lastName;

    private String nationalCode;

    private UUID avatarMediaId;

    private EntityIdDto gender;

    private LocalDate birthDate;

    private List<UUID> roleIds;

}