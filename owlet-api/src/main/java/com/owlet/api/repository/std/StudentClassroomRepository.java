package com.owlet.api.repository.std;

import com.owlet.api.domain.std.StudentClassroom;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentClassroomRepository
        extends BaseRepository<StudentClassroom, UUID> {
    List<StudentClassroom> findAllByClassroom_Id(UUID classroomId);

    @Modifying
    @Query("UPDATE StudentClassroom sc SET sc.active = :active WHERE sc.id IN :ids")
    void updateStatusByIds(@Param("ids") List<UUID> ids, @Param("active") Boolean active);

    StudentClassroom findByStudentIdAndActiveTrue(UUID studentId);

    // دریافت شناسه‌های تمام دانش‌آموزانی که در حال حاضر در هر کلاسی فعال هستند
    @Query("select distinct sc.student.id from StudentClassroom sc where sc.active = true and sc.deleted = false")
    List<UUID> findActiveStudentIds();

    // غیرفعال‌سازی کلاس‌های فعال قبلی یک لیست از دانش‌آموزان
    @Modifying
    @Query("update StudentClassroom sc set sc.active = false where sc.student.id in :studentIds and sc.active = true and sc.deleted = false")
    void deactivatePreviousAssignments(@Param("studentIds") List<UUID> studentIds);

    List<StudentClassroom> findByClassroomIdAndDeletedFalse(UUID classroomId);
}
