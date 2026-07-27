package com.owlet.api.service.edu;

import com.owlet.api.dto.edu.ProductCreateRequest;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.service.base.CrudService;

import java.util.UUID;

public interface ProductService extends CrudService<
        UUID,
        ProductDto,
        ProductCreateRequest,
        ProductCreateRequest> {
}