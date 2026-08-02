package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.StudentAchievement;
import com.owlet.api.dto.std.StudentAchievementCreateRequest;
import com.owlet.api.dto.std.StudentAchievementDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface StudentAchievementMapper extends CrudMapper<
        StudentAchievement,
        StudentAchievementDto,
        StudentAchievementCreateRequest,
        StudentAchievementCreateRequest> {


    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "achievementType",
            source = "achievementType",
            qualifiedByName = "toReference")
    @Override
    StudentAchievement toEntity(StudentAchievementCreateRequest StudentAchievementCreateRequest);


    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "achievementType",
            source = "achievementType",
            qualifiedByName = "toReference")
    @Override
    void update(StudentAchievementCreateRequest StudentAchievementCreateRequest, @MappingTarget StudentAchievement StudentAchievement);
}