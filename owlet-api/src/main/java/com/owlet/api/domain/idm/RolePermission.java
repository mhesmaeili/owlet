package com.owlet.api.domain.idm;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "role_permission", schema = "idm")
public class RolePermission extends UuidEntity {


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "granted", nullable = false)
    private Boolean granted = false;

}