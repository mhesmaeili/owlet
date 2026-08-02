package com.owlet.api.repository.std;

import com.owlet.api.domain.std.StudentParent;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentParentRepository
        extends BaseRepository<StudentParent, UUID> {
}
