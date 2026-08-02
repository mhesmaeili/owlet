package com.owlet.api.repository.std;

import com.owlet.api.domain.std.StudentAchievement;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentAchievementRepository
        extends BaseRepository<StudentAchievement, UUID> {
}
