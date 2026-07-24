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

@Getter
@Setter
@Entity
@Table(name = "classroom", schema = "org")
public class Classroom extends UuidEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 300)
    @NotNull
    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    private ReferenceItem grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_account_id")
    private Account teacherAccount;

    @ColumnDefault("30")
    @Column(name = "capacity")
    private Integer capacity;

    @ColumnDefault("true")
    @Column(name = "active")
    private Boolean active = true;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "academic_years_id", nullable = false)
    private AcademicYear academicYears;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

}