package com.owlet.api.domain.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;

@Getter
@Setter
@Entity
@Table(name = "attachment", schema = "base")
@Filter(name = "deletedFilter")
public class Attachment extends UuidEntity {
    @Size(max = 500)
    @NotNull
    @Column(name = "filename", nullable = false, length = 500)
    private String filename;

    @Size(max = 255)
    @NotNull
    @Column(name = "mime_type", nullable = false)
    private String mimeType;


    @Column(name = "size")
    private Long size;

    @Size(max = 1024)
    @Column(name = "object_key", length = 1024)
    private String objectKey;

    @Size(max = 1024)
    @Column(name = "sha256", length = 1024)
    private String sha256;
}