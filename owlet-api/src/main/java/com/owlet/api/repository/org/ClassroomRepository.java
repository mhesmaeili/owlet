package com.owlet.api.repository.org;

import com.owlet.api.annotation.IncludeDeleted;
import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.org.Classroom;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository
        extends BaseRepository<Classroom, UUID> {

    @IncludeDeleted
    List<Classroom> findByTitle(String title);

    @Query("SELECT distinct s.classroom " +
            "FROM TeacherClassroom s " +
            "WHERE s.teacherAccount.id = :accountId And s.classroom.school.id=:schoolId And s.active = true")
    List<Classroom> teacherSteamClassroom(@Param("accountId") UUID accountId ,@Param("schoolId") UUID schoolId);

    Long countBySchoolIdAndTeacherAccountIdAndActiveTrue(UUID school_id, UUID teacherAccount_id);

}
