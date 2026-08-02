package com.owlet.api.domain.std;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student_achievement", schema = "std")
public class StudentAchievement extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @NotNull
    @Column(name = "achievement", nullable = false, length = Integer.MAX_VALUE)
    private String achievement;

    @NotNull
    @Column(name = "achievement_date", nullable = false)
    private LocalDate achievementDate;

}