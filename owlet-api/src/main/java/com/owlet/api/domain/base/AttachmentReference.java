package com.owlet.api.domain.base;

import com.owlet.api.domain.ref.ReferenceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "attachment_reference", schema = "base")
@Filter(name = "deletedFilter")
public class AttachmentReference extends UuidEntity {
    @Size(max = 100)
    @NotNull
    @Column(name = "entity_class", nullable = false, length = 100)
    private String entityClass;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ReferenceItem category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

}