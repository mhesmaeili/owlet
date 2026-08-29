package com.owlet.api.repository.ses;

import com.owlet.api.domain.ses.Session;
import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrainingCourseRepository
        extends BaseRepository<TrainingCourse, UUID> {

    @Query("SELECT distinct s " +
            "FROM Session s " +
            "WHERE s.trainingCourse.classroom.id = :classroomId " +
            "And s.trainingCourse.teacherAccount.id=:accountId And s.trainingCourse.active = true " +
            "order by s.startTime")
    List<Session> teacherSteamCourse(@Param("accountId") UUID accountId,@Param("classroomId") UUID classroomId);

    @Query("SELECT count (distinct s.classroom) " +
            "FROM TrainingCourse s " +
            "WHERE s.classroom.school.id = :schoolId " +
            "And s.active=true And s.teacherAccount.id = :teacherId")
    Long countDistinctClassroomsBySchoolAndTeacher(UUID schoolId, UUID teacherId);

    @Query("SELECT distinct t " +
            "FROM TrainingCourse t " +
            "join Session s on s.trainingCourse.id = t.id " +
            "join SessionStudent ss on ss.session.id=s.id " +
            "WHERE ss.student.id=:studentId")
    List<TrainingCourse> findAllByStudentId(@Param("studentId") UUID studentId);
}
