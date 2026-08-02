package com.owlet.api.service.std.impl;

import com.owlet.api.domain.std.StudentAchievement;
import com.owlet.api.dto.std.StudentAchievementCreateRequest;
import com.owlet.api.dto.std.StudentAchievementDto;
import com.owlet.api.mapper.std.StudentAchievementMapper;
import com.owlet.api.repository.std.StudentAchievementRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.std.StudentAchievementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class StudentAchievementImpl extends CrudServiceImpl<
        StudentAchievement,
        UUID,
        StudentAchievementDto,
        StudentAchievementCreateRequest,
        StudentAchievementCreateRequest,
        StudentAchievementRepository,
        StudentAchievementMapper>
        implements StudentAchievementService {

    public StudentAchievementImpl(
            StudentAchievementRepository repository,
            StudentAchievementMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<StudentAchievement> entityClass() {
        return StudentAchievement.class;
    }
}