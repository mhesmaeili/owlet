package com.owlet.api.mapper.profile.school;

import com.owlet.api.domain.org.School;
import com.owlet.api.dto.profile.school.TeacherSchoolDto;
import com.owlet.api.mapper.base.BaseMapper;
import com.owlet.api.mapper.base.BaseMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface TeacherSchoolMapper extends BaseMapper<
        School,
        TeacherSchoolDto> {
}