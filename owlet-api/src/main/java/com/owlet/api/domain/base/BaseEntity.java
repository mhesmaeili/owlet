package com.owlet.api.domain.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> implements Serializable {

    @Transient
    private PK id;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;


    @CreatedBy
    @Column(name = "created_by")
    private UUID createdBy;


    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @LastModifiedBy
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
