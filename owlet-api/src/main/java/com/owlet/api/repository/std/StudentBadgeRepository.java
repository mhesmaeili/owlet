package com.owlet.api.repository.std;

import com.owlet.api.domain.std.StudentBadge;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentBadgeRepository
        extends BaseRepository<StudentBadge, UUID> {
    List<StudentBadge> findBySessionStudent_Session_IdAndBadgeType_Id(UUID sessionId, UUID badgeTypeId);
}
