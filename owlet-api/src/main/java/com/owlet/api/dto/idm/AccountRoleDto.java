package com.owlet.api.dto.idm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
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
public class AccountRoleDto extends BaseDto<UUID> {

    private RoleDto role;
    private AccountDto account;
    private OffsetDateTime assignedAt;
    private OffsetDateTime expiredAt;
    private Boolean active;
}