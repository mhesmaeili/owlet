package com.owlet.api.controller.edu;

import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.edu.ProductCreateRequest;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.service.edu.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name = "ProductController")
@RestController
@RequestMapping("/api/edu/product")
public class ProductController extends CrudController<
        UUID,
        ProductDto,
        ProductCreateRequest,
        ProductCreateRequest> {

    public ProductController(ProductService service) {
        super(service);
    }

}