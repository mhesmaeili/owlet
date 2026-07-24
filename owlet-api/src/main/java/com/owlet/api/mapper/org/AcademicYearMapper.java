package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.AcademicYear;
import com.owlet.api.dto.org.AcademicYearCreateRequest;
import com.owlet.api.dto.org.AcademicYearDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface AcademicYearMapper extends CrudMapper<
        AcademicYear,
        AcademicYearDto,
        AcademicYearCreateRequest,
        AcademicYearCreateRequest> {

}
