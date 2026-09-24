package com.owlet.api.domain.idm;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "refresh_token", schema = "idm")
public class RefreshToken extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Size(max = 1000)
    @NotNull
    @Column(name = "token", nullable = false, length = 1000)
    private String token;

    @NotNull
    @Column(name = "issued_at", nullable = false)
    private OffsetDateTime issuedAt;

    @NotNull
    @Column(name = "expired_at", nullable = false)
    private OffsetDateTime expiredAt;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "revoked", nullable = false)
    private Boolean revoked = false;

    @Column(name = "revoked_at")
    private OffsetDateTime revokedAt;

    @Size(max = 500)
    @Column(name = "revoke_reason", length = 500)
    private String revokeReason;

    @Size(max = 100)
    @Column(name = "ip_address", length = 100)
    private String ipAddress;

    @Size(max = 1000)
    @Column(name = "user_agent", length = 1000)
    private String userAgent;

    @Column(name = "used_at")
    private OffsetDateTime usedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private RefreshSession session;
}