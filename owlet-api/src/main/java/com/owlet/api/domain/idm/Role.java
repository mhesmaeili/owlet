package com.owlet.api.domain.idm;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role", schema = "idm")
public class Role extends UuidEntity {


    @Size(max = 100)
    @NotNull
    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "system_role", nullable = false)
    private Boolean systemRole = false;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @OneToMany(mappedBy = "role")
    private Set<AccountRole> accountRoles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<RolePermission> rolePermissions = new LinkedHashSet<>();

}