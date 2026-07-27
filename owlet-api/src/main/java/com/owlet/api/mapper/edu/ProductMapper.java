package com.owlet.api.mapper.edu;

import com.owlet.api.domain.edu.Product;
import com.owlet.api.dto.edu.ProductCreateRequest;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.mapper.base.BaseMapperConfig;
import com.owlet.api.mapper.base.CrudMapper;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface ProductMapper extends CrudMapper<
        Product,
        ProductDto,
        ProductCreateRequest,
        ProductCreateRequest> {


}