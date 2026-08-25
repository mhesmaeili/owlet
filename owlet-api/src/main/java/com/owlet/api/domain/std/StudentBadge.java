package com.owlet.api.domain.std;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.domain.ses.SessionStudent;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student_badge", schema = "std")
@Filter(name = "deletedFilter")
public class StudentBadge extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "session_student_id", nullable = false)
    private SessionStudent sessionStudent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "badge_type_id", nullable = false)
    private ReferenceItem badgeType;

    @Size(max = 300)
    @Column(name = "title", length = 300)
    private String title;

    @Size(max = 1000)
    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "achieved_at")
    private LocalDate achievedAt;

}