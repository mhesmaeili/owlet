package com.owlet.api.repository.std;

import com.owlet.api.domain.std.Student;
import com.owlet.api.domain.std.StudentParent;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentParentRepository
        extends BaseRepository<StudentParent, UUID> {


    @Query("SELECT s.student " +
            "FROM StudentParent s " +
            "WHERE s.parent.account.id = :parentId And s.active=true")
    List<Student> findStudentByParentId(@Param("parentId") UUID parentId);
}
