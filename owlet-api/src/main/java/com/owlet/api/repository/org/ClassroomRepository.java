package com.owlet.api.repository.org;

import com.owlet.api.annotation.IncludeDeleted;
import com.owlet.api.domain.idm.Account;
import com.owlet.api.domain.org.Classroom;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassroomRepository
        extends BaseRepository<Classroom, UUID> {

    @IncludeDeleted
    List<Classroom> findByTitle(String title);
}
