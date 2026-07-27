package com.owlet.api.mapper.std;

import com.owlet.api.domain.std.StudentAttendanceAbsence;
import com.owlet.api.dto.std.StudentAttendanceAbsenceCreateRequest;
import com.owlet.api.dto.std.StudentAttendanceAbsenceDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(config = BaseMapperConfig.class)
public interface StudentAttendanceAbsenceMapper extends CrudMapper<
        StudentAttendanceAbsence,
        StudentAttendanceAbsenceDto,
        StudentAttendanceAbsenceCreateRequest,
        StudentAttendanceAbsenceCreateRequest> {

    @Mapping(target = "studentClassroom",
            source = "studentClassroom",
            qualifiedByName = "toReference")
    @Override
    StudentAttendanceAbsence toEntity(StudentAttendanceAbsenceCreateRequest studentAttendanceAbsenceCreateRequest);

    @Mapping(target = "studentClassroom",
            source = "studentClassroom",
            qualifiedByName = "toReference")
    @Override
    void update(StudentAttendanceAbsenceCreateRequest studentAttendanceAbsenceCreateRequest,@MappingTarget StudentAttendanceAbsence studentAttendanceAbsence);
}