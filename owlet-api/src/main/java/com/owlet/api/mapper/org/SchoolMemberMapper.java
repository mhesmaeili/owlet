package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.SchoolMember;
import com.owlet.api.dto.org.SchoolMemberCreateRequest;
import com.owlet.api.dto.org.SchoolMemberDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface SchoolMemberMapper extends CrudMapper<
        SchoolMember,
        SchoolMemberDto,
        SchoolMemberCreateRequest,
        SchoolMemberCreateRequest> {

    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")
    @Mapping(target = "role",
            source = "role",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Override
    SchoolMember toEntity(SchoolMemberCreateRequest schoolMemberCreateRequest);

    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")
    @Mapping(target = "role",
            source = "role",
            qualifiedByName = "toReference")
    @Mapping(target = "account",
            source = "account",
            qualifiedByName = "toReference")
    @Override
    void update(SchoolMemberCreateRequest schoolMemberCreateRequest,@MappingTarget SchoolMember schoolMember);


}