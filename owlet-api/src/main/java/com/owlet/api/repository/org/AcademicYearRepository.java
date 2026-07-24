package com.owlet.api.repository.org;

import com.owlet.api.domain.org.AcademicYear;
import com.owlet.api.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AcademicYearRepository
        extends BaseRepository<AcademicYear, UUID> {
}
