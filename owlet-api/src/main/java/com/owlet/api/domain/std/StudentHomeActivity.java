package com.owlet.api.domain.std;

import com.owlet.api.domain.base.UuidEntity;
import com.owlet.api.domain.ses.SessionStudent;
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
    @JoinColumn(name = "session_student_id", nullable = false)
    private SessionStudent sessionStudent;

    @Column(name = "parent_response")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> parentResponse;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

}