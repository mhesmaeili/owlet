package com.owlet.api.service.org;

import com.owlet.api.domain.org.School;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.mapper.org.SchoolMapper;
import com.owlet.api.repository.org.SchoolRepository;
import com.owlet.api.service.base.CrudServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SchoolServiceImpl extends CrudServiceImpl<
        School,
        UUID,
        SchoolDto,
        SchoolDto,
        SchoolDto>
        implements SchoolService {

    public SchoolServiceImpl(
            SchoolRepository repository,
            SchoolMapper mapper) {

        super(repository, mapper);
    }
}