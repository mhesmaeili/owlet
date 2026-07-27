package com.owlet.api.domain.std;

import com.owlet.api.domain.base.UuidEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student_attendance_absence", schema = "std")
public class StudentAttendanceAbsence extends UuidEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_classroom_id", nullable = false)
    private StudentClassroom studentClassroom;

    @NotNull
    @Column(name = "attendance_date", nullable = false)
    private LocalDate attendanceDate;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "present", nullable = false)
    private Boolean present = true;

}