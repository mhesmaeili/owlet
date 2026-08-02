package com.owlet.api.repository.ses;

import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrainingCourseRepository
        extends BaseRepository<TrainingCourse, UUID> {
}
