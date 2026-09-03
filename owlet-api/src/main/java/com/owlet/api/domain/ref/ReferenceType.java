package com.owlet.api.domain.ref;

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
import org.hibernate.annotations.Filter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "reference_type", schema = "ref")
@Filter(name = "deletedFilter")
public class ReferenceType extends UuidEntity {

    @NotNull
    @ColumnDefault("0")
    @Column(name = "version", nullable = false)
    private Long version;

    @Size(max = 100)
    @NotNull
    @Column(name = "code", nullable = false, length = 100)
    private String code;

    @Size(max = 200)
    @NotNull
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Size(max = 500)
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @OneToMany(mappedBy = "referenceType")
    private Set<ReferenceItem> referenceItems = new LinkedHashSet<>();

}