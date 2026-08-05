package com.owlet.api.repository.org;

import com.owlet.api.domain.org.School;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchoolRepository
        extends BaseRepository<School, UUID> {

    @Query("SELECT s.school " +
            "FROM SchoolMember s " +
            "WHERE s.account.id = :accountId And s.role.code=:roleCode And s.active = true")
    List<School> findSchoolByTeacherId(@Param("accountId") UUID accountId , @Param("roleCode") String roleCode);
}
