package com.owlet.api.mapper.org;

import com.owlet.api.domain.org.Branch;
import com.owlet.api.dto.org.BranchCreateRequest;
import com.owlet.api.dto.org.BranchDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = BaseMapperConfig.class)
public interface BranchMapper extends CrudMapper<
        Branch,
        BranchDto,
        BranchCreateRequest,
        BranchCreateRequest> {

    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")

    @Mapping(target = "managerAccount",
            source = "managerAccount",
            qualifiedByName = "toReference")
    Branch toEntity(BranchCreateRequest dto);


    @Mapping(target = "school",
            source = "school",
            qualifiedByName = "toReference")

    @Mapping(target = "managerAccount",
            source = "managerAccount",
            qualifiedByName = "toReference")
    @Override
    void update(BranchCreateRequest branchCreateRequest, @MappingTarget Branch Branch);

}
