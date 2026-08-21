package com.owlet.api.mapper.profile.school;

import com.owlet.api.domain.org.Classroom;
import com.owlet.api.dto.profile.school.ProfileTeacherClassroomDto;
import com.owlet.api.mapper.base.BaseMapper;
import com.owlet.api.mapper.base.BaseMapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ProfileTeacherClassroomMapper extends BaseMapper<
        Classroom,
        ProfileTeacherClassroomDto> {
}