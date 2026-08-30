package com.owlet.api.domain.std;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.ref.ReferenceItem;
import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.domain.ses.TrainingCourse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "student_home_activity", schema = "std")
public class StudentHomeActivity extends UuidEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "training_course_id", nullable = false)
    private TrainingCourse trainingCourse;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "level_of_enthusiasm")
    private Short levelOfEnthusiasm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "independence_construction_id")
    private ReferenceItem independenceConstruction;

    @Column(name = "parent_response", length = Integer.MAX_VALUE)
    private String parentResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

}