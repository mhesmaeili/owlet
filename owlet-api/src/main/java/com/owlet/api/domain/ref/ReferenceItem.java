package com.owlet.api.domain.ref;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Filter;

@Getter
@Setter
@Entity
@Table(name = "reference_item", schema = "ref")
@Filter(name = "deletedFilter")
public class ReferenceItem extends UuidEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reference_type_id", nullable = false)
    private ReferenceType referenceType;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 200)
    @Column(name = "title", length = 200)
    private String title;

    @NotNull
    @Size(max = 200)
    @Column(name = "title_fa", nullable = false, length = 200)
    private String titleFa;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Size(max = 50)
    @Column(name = "color", length = 50)
    private String color;

    @Size(max = 200)
    @Column(name = "icon", length = 200)
    private String icon;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = true;

}