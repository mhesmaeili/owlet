package com.owlet.api.domain.base;

import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.domain.std.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "qrcode", schema = "base")
public class Qrcode extends UuidEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private ReferenceItem status;

    @Column(name = "assigned_at")
    private OffsetDateTime assignedAt;

    @Size(max = 20)
    @Column(name = "code", length = 20)
    private String code;

}