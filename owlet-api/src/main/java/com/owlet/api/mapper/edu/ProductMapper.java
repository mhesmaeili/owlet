package com.owlet.api.mapper.edu;

import com.owlet.api.domain.edu.Product;
import com.owlet.api.dto.edu.ProductCreateRequest;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = BaseMapperConfig.class)
public interface ProductMapper extends CrudMapper<
        Product,
        ProductDto,
        ProductCreateRequest,
        ProductCreateRequest> {

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void update(ProductCreateRequest productCreateRequest, @MappingTarget Product product);
}