package com.owlet.api.domain.ses;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.std.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "teacher_observation", schema = "ses")
public class TeacherObservation extends UuidEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_account_id")
    private Account teacherAccount;

    @NotNull
    @Column(name = "observation", nullable = false, length = Integer.MAX_VALUE)
    private String observation;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_student_id")
    private SessionStudent sessionStudent;

}