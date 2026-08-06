package com.owlet.api.service.ses.impl;

import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.mapper.ses.SessionStudentMapper;
import com.owlet.api.repository.ses.SessionStudentRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.ses.SessionStudentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class SessionStudentImpl extends CrudServiceImpl<
        SessionStudent,
        UUID,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest,
        SessionStudentRepository,
        SessionStudentMapper>
        implements SessionStudentService {

    public SessionStudentImpl(
            SessionStudentRepository repository,
            SessionStudentMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }


    @Override
    protected Class<SessionStudent> entityClass() {
        return SessionStudent.class;
    }

    @Transactional
    @Override
    public List<SessionStudentDto> updateAttendance(UUID sessionId, List<UUID> studentIds, boolean present) {
        List<SessionStudent> list = repository.findBySession_IdAndStudent_IdIn(sessionId, studentIds);
        list.forEach(ss -> ss.setPresent(true));
        return mapper.toDto(list);
    }

    @Override
    protected String[] getSearchableFields() {
        return new String[]{
                "student.firstName",
                "student.lastName",
                "student.studentNo",
                "student.nationalCode"
        };
    }

    @Override
    public Page<SessionStudentDto> getStudentsBySession(UUID sessionId, String keyword, Pageable pageable) {

        Specification<SessionStudent> spec = (root, query, cb) -> cb.and(
                cb.equal(root.get("deleted"), false),
                cb.equal(root.get("session").get("id"), sessionId)
        );

        Specification<SessionStudent> keywordSpec = buildSearchSpecification(keyword);
        if (keywordSpec != null) {
            spec = spec.and(keywordSpec);
        }

        return repository.findAll(spec, pageable).map(mapper::toDto);
    }
}