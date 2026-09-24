package com.owlet.api.domain.idm;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "refresh_session", schema = "idm")
public class RefreshSession extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NotNull
    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @NotNull
    @Column(name = "expired_at", nullable = false)
    private OffsetDateTime expiredAt;

    @Column(name = "revoked_at")
    private OffsetDateTime revokedAt;

    @Size(max = 500)
    @Column(name = "revoke_reason", length = 500)
    private String revokeReason;

}