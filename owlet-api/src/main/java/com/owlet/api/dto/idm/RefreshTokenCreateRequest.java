package com.owlet.api.dto.idm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.owlet.api.dto.BaseDto;
import com.owlet.api.service.base.helper.EntityIdDto;
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
public class RefreshTokenCreateRequest extends BaseDto<UUID> {

    private EntityIdDto account;

    private String token;

    private OffsetDateTime issuedAt;

    private OffsetDateTime expiredAt;

    private Boolean revoked = false;

    private OffsetDateTime revokedAt;

    private String revokeReason;

    private String ipAddress;

    private String userAgent;

}