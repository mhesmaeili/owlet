package com.owlet.api.domain.edu;

import com.owlet.api.domain.base.UuidEntity;
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
@Table(name = "product", schema = "edu")
public class Product extends UuidEntity {

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 300)
    @NotNull
    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @Size(max = 1000)
    @Column(name = "short_description", length = 1000)
    private String shortDescription;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade_id")
    private ReferenceItem grade;

    @Column(name = "age_from")
    private Short ageFrom;

    @Column(name = "age_to")
    private Short ageTo;

    @Column(name = "estimated_duration")
    private Integer estimatedDuration;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "version_no")
    private Integer versionNo = 1;

}