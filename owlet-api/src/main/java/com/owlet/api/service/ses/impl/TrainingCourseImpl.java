package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.TrainingCourse;
import com.owlet.api.dto.ses.TrainingCourseCreateRequest;
import com.owlet.api.dto.ses.TrainingCourseDto;
import com.owlet.api.mapper.ses.TrainingCourseMapper;
import com.owlet.api.repository.ses.TrainingCourseRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.TrainingCourseService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class TrainingCourseImpl extends CrudServiceImpl<
        TrainingCourse,
        UUID,
        TrainingCourseDto,
        TrainingCourseCreateRequest,
        TrainingCourseCreateRequest,
        TrainingCourseRepository,
        TrainingCourseMapper>
        implements TrainingCourseService {

    public TrainingCourseImpl(
            TrainingCourseRepository repository,
            TrainingCourseMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<TrainingCourse> entityClass() {
        return TrainingCourse.class;
    }
}