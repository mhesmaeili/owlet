package com.owlet.api.mapper.base;

import com.owlet.api.domain.base.Qrcode;
import com.owlet.api.dto.base.QrcodeCreateRequest;
import com.owlet.api.dto.base.QrcodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(config = BaseMapperConfig.class)
public interface QrcodeMapper extends CrudMapper<
        Qrcode,
        QrcodeDto,
        QrcodeCreateRequest,
        QrcodeCreateRequest> {

    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "status",
            source = "status",
            qualifiedByName = "toReference")
    @Override
    Qrcode toEntity(QrcodeCreateRequest qrcodeCreateRequest);

    @Mapping(target = "student",
            source = "student",
            qualifiedByName = "toReference")
    @Mapping(target = "status",
            source = "status",
            qualifiedByName = "toReference")
    @Override
    void update(QrcodeCreateRequest qrcodeCreateRequest, @MappingTarget Qrcode qrcode);
}