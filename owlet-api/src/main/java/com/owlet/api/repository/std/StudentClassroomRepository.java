package com.owlet.api.repository.std;

import com.owlet.api.domain.std.StudentClassroom;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentClassroomRepository
        extends BaseRepository<StudentClassroom, UUID> {
    List<StudentClassroom> findAllByClassroom_Id(UUID classroomId);
}
