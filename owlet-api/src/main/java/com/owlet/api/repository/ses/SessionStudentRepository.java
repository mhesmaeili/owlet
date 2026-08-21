package com.owlet.api.repository.ses;

import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface SessionStudentRepository
        extends BaseRepository<SessionStudent, UUID> {
    List<SessionStudent> findBySession_IdAndStudent_IdIn(
            UUID sessionId,
            Collection<UUID> studentIds
    );

    @Query("SELECT COUNT(ss) FROM SessionStudent ss WHERE " +
            "ss.session.trainingCourse.active=true " +
            "and ss.session.trainingCourse.classroom.id = :classroomId")
    long countByClassroomId(@Param("classroomId") UUID classroomId);
}
