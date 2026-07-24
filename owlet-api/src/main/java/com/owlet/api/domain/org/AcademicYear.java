package com.owlet.api.domain.org;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "academic_years", schema = "org")
public class AcademicYear extends UuidEntity {
    @Size(max = 50)
    @NotNull
    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @OneToMany
    @JoinColumn(name = "academic_years_id")
    private Set<Classroom> classrooms = new LinkedHashSet<>();

}