package com.owlet.api.domain.idm;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "account_role", schema = "idm")
public class AccountRole extends UuidEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "assigned_at", nullable = false)
    private OffsetDateTime assignedAt;

    @Column(name = "expired_at")
    private OffsetDateTime expiredAt;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = false;

}