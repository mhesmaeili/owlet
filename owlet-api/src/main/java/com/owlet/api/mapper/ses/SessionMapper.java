package com.owlet.api.mapper.ses;


import com.owlet.api.domain.ses.Session;
import com.owlet.api.dto.ses.SessionCreateRequest;
import com.owlet.api.dto.ses.SessionDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface SessionMapper extends CrudMapper<
        Session,
        SessionDto,
        SessionCreateRequest,
        SessionCreateRequest> {

    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "product",
            source = "product",
            qualifiedByName = "toReference")
    @Override
    void update(SessionCreateRequest sessionCreateRequest, @MappingTarget Session session);

    @Mapping(target = "classroom",
            source = "classroom",
            qualifiedByName = "toReference")
    @Mapping(target = "teacherAccount",
            source = "teacherAccount",
            qualifiedByName = "toReference")
    @Mapping(target = "product",
            source = "product",
            qualifiedByName = "toReference")
    @Override
    Session toEntity(SessionCreateRequest sessionCreateRequest);


}