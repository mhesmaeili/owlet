package com.owlet.api.mapper.ses;


import com.owlet.api.domain.ses.SessionStudent;
import com.owlet.api.dto.ses.SessionStudentCreateRequest;
import com.owlet.api.dto.ses.SessionStudentDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface SessionStudentMapper extends CrudMapper<
        SessionStudent,
        SessionStudentDto,
        SessionStudentCreateRequest,
        SessionStudentCreateRequest> {

    @Mapping(target = "session",
            source = "session",
            qualifiedByName = "toReference")
    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Override
    void update(SessionStudentCreateRequest sessionStudentCreateRequest, @MappingTarget SessionStudent sessionStudent);

    @Mapping(target = "session",
            source = "session",
            qualifiedByName = "toReference")
    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Override
    SessionStudent toEntity(SessionStudentCreateRequest sessionStudentCreateRequest);


}