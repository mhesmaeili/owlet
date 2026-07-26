package com.owlet.api.repository.org;

import com.owlet.api.domain.org.TeacherClassroom;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeacherClassroomRepository
        extends BaseRepository<TeacherClassroom, UUID> {
}
