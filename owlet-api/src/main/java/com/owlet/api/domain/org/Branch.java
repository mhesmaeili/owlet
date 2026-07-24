package com.owlet.api.domain.org;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.idm.Account;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "branch", schema = "org")
public class Branch extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 300)
    @NotNull
    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_account_id")
    private Account managerAccount;

    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    @Size(max = 30)
    @Column(name = "mobile", length = 30)
    private String mobile;

    @Size(max = 1000)
    @Column(name = "address", length = 1000)
    private String address;

    @OneToMany
    @JoinColumn(name = "branch_id")
    private Set<Classroom> classrooms = new LinkedHashSet<>();

}