package com.owlet.api.domain.idm;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.ref.ReferenceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "account", schema = "idm")
public class Account extends UuidEntity {

    @Size(max = 100)
    @NotNull
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 20)
    @Column(name = "mobile", length = 20)
    private String mobile;

    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    @Size(max = 500)
    @NotNull
    @Column(name = "password_hash", nullable = false, length = 500)
    private String passwordHash;

    @Size(max = 100)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Size(max = 100)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Size(max = 20)
    @Column(name = "national_code", length = 20)
    private String nationalCode;

    @Column(name = "avatar_media_id")
    private UUID avatarMediaId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender_id")
    private ReferenceItem gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "locked", nullable = false)
    private Boolean locked = false;

    @Column(name = "last_login_at")
    private OffsetDateTime lastLoginAt;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "failed_login_count", nullable = false)
    private Integer failedLoginCount;

    @Column(name = "password_changed_at")
    private OffsetDateTime passwordChangedAt;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountRole> accountRoles = new LinkedHashSet<>();

}