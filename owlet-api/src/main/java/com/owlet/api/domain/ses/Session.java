package com.owlet.api.domain.ses;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.edu.Product;
import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.org.Classroom;
import com.owlet.api.domain.ref.ReferenceItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "session", schema = "ses")
public class Session extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_account_id", nullable = false)
    private Account teacherAccount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_status_id", nullable = true)
    private ReferenceItem sessionStatus;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @NotNull
    @Size(max = 300)
    @Column(name = "title", length = 300, nullable = false)
    private String title;

    @Column(name = "session_date")
    private LocalDate sessionDate;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "ai_summary", length = Integer.MAX_VALUE)
    private String aiSummary;

    @Column(name = "teacher_summary", length = Integer.MAX_VALUE)
    private String teacherSummary;


    @ColumnDefault("false")
    @Column(name = "finalized")
    private Boolean finalized = false;

    @Column(name = "finalized_at")
    private OffsetDateTime finalizedAt;

}