package com.owlet.api.service.org;

import com.owlet.api.dto.org.SchoolCreateRequest;
import com.owlet.api.dto.org.SchoolDto;
import com.owlet.api.dto.org.SchoolUpdateRequest;
import com.owlet.api.dto.profile.school.TeacherSchoolDto;
import com.owlet.api.service.base.CrudService;

import java.util.List;
import java.util.UUID;

public interface SchoolService extends CrudService<
        UUID,
        SchoolDto,
        SchoolCreateRequest,
        SchoolUpdateRequest> {

    List<TeacherSchoolDto> teacherSteamWorkWithSchool();
}