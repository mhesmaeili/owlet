package com.owlet.api.domain.ses;

import com.owlet.api.converter.StringListConverter;
import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.domain.std.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Filter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "session_student", schema = "ses")
@Filter(name = "deletedFilter")
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

    @ColumnDefault("true")
    @Column(name = "present")
    private Boolean present = true;

    @ColumnDefault("false")
    @Column(name = "completed")
    private Boolean completed = false;

    @ColumnDefault("false")
    @Column(name = "ai_processed")
    private Boolean aiProcessed = false;

    @Column(name = "point")
    private Integer point;

    @Column(name = "time_base")
    private Boolean timeBase;

    @Column(name = "number")
    private Integer number;

    @Column(name = "ai_result", length = Integer.MAX_VALUE)
    private String aiResult;

    @Column(name = "short_description", length = Integer.MAX_VALUE)
    private String shortDescription;

    @Column(name = "point_description", length = Integer.MAX_VALUE)
    private String pointDescription;

    @Column(name = "state_evaluation", length = Integer.MAX_VALUE)
    private String stateEvaluation;

    @Convert(converter = StringListConverter.class)
    @Column(name = "soft_skills_selected", length = Integer.MAX_VALUE)
    private List<String> softSkillsSelected;


    @Column(name = "elapsed_time")
    private Integer elapsedTime;

    @Column(name = "last_photo_date")
    private OffsetDateTime lastPhotoDate;

}