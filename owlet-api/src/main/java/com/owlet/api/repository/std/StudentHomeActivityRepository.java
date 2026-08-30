package com.owlet.api.repository.std;

import com.owlet.api.domain.std.StudentHomeActivity;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentHomeActivityRepository
        extends BaseRepository<StudentHomeActivity, UUID> {
    StudentHomeActivity findByTrainingCourseIdAndStudentId(UUID courseId, UUID studentId);
}
