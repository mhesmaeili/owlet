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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_status_id")
    private ReferenceItem attendanceStatus;

    @Column(name = "attendance_time")
    private OffsetDateTime attendanceTime;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "present", nullable = false)
    private Boolean present = false;

    @ColumnDefault("false")
    @Column(name = "completed")
    private Boolean completed = false;

    @ColumnDefault("false")
    @Column(name = "ai_processed")
    private Boolean aiProcessed = false;

}