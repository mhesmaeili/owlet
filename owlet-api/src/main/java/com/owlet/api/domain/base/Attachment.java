package com.owlet.api.domain.base;

import com.owlet.api.domain.ref.ReferenceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "attachment", schema = "base")
public class Attachment extends UuidEntity {
    @Size(max = 500)
    @NotNull
    @Column(name = "filename", nullable = false, length = 500)
    private String filename;

    @Size(max = 255)
    @NotNull
    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Size(max = 100)
    @NotNull
    @Column(name = "entity_class", nullable = false, length = 100)
    private String entityClass;

    @NotNull
    @Column(name = "entity_id", nullable = false)
    private UUID entityId;

    @Column(name = "size")
    private Long size;

    @Size(max = 1024)
    @Column(name = "object_key", length = 1024)
    private String objectKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ReferenceItem category;

    @Size(max = 1024)
    @Column(name = "sha256", length = 1024)
    private String sha256;
}