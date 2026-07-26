package com.owlet.api.domain.ses;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.domain.std.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "session_student", schema = "ses")
public class SessionStudent extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attendance_status_id", nullable = false)
    private ReferenceItem attendanceStatus;

    @Column(name = "attendance_time")
    private OffsetDateTime attendanceTime;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "present", nullable = false)
    private Boolean present = false;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "ai_processed", nullable = false)
    private Boolean aiProcessed = false;

}