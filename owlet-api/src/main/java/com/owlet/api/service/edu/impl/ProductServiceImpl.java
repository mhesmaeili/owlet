package com.owlet.api.service.edu.impl;

import com.owlet.api.domain.edu.Product;
import com.owlet.api.dto.edu.ProductCreateRequest;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.mapper.edu.ProductMapper;
import com.owlet.api.repository.edu.ProductRepository;
import com.owlet.api.security.AuditableService;
import com.owlet.api.service.base.CrudServiceImpl;
import com.owlet.api.service.edu.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl extends CrudServiceImpl<
        Product,
        UUID,
        ProductDto,
        ProductCreateRequest,
        ProductCreateRequest,
        ProductRepository,
        ProductMapper>
        implements ProductService {

    public ProductServiceImpl(
            ProductRepository repository,
            ProductMapper mapper,
            AuditableService auditableService) {

        super(repository, mapper, auditableService);
    }

    @Override
    protected Class<Product> entityClass() {
        return Product.class;
    }


    @Override
    protected String[] getSearchableFields() {
        return new String[]{
                "title",
                "code",
                "shortDescription"
        };
    }
}