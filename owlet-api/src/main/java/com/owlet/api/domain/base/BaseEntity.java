package com.owlet.api.domain.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    @Id
    private PK id;


    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;


    @Column(name = "created_by")
    private UUID createdBy;


    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;


    @Column(name = "updated_by")
    private UUID updatedBy;


    @Column(nullable = false)
    private Boolean deleted = false;


    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;


    @Column(name = "deleted_by")
    private UUID deletedBy;


    @Version
    @Column(nullable = false)
    private Long version = 0L;
}
