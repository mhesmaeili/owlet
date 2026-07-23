package com.owlet.api.domain.org;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.ref.ReferenceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "school", schema = "org")
public class School extends UuidEntity {


    @Size(max = 100)
    @NotNull
    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Size(max = 300)
    @NotNull
    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_type_id", nullable = false)
    private ReferenceItem schoolType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_school_id")
    private School parentSchool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_account_id")
    private Account managerAccount;

    @Size(max = 50)
    @Column(name = "national_code", length = 50)
    private String nationalCode;

    @Size(max = 50)
    @Column(name = "economic_code", length = 50)
    private String economicCode;

    @Size(max = 30)
    @Column(name = "phone", length = 30)
    private String phone;

    @Size(max = 30)
    @Column(name = "mobile", length = 30)
    private String mobile;

    @Size(max = 200)
    @Column(name = "email", length = 200)
    private String email;

    @Size(max = 300)
    @Column(name = "website", length = 300)
    private String website;

    @Size(max = 100)
    @Column(name = "province", length = 100)
    private String province;

    @Size(max = 100)
    @Column(name = "city", length = 100)
    private String city;

    @Size(max = 1000)
    @Column(name = "address", length = 1000)
    private String address;

    @Size(max = 30)
    @Column(name = "postal_code", length = 30)
    private String postalCode;

    @Column(name = "latitude", precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 7)
    private BigDecimal longitude;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;


}
