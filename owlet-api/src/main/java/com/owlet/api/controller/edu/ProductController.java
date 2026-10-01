package com.owlet.api.controller.edu;

import com.owlet.api.annotation.PublicEndpoint;
import com.owlet.api.controller.base.CrudController;
import com.owlet.api.dto.edu.ProductCreateRequest;
import com.owlet.api.dto.edu.ProductDto;
import com.owlet.api.service.edu.ProductService;
import com.owlet.common.response.ApiResponse;
import com.owlet.common.response.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "ProductController")
@RestController
@RequestMapping("/api/edu/product")
public class ProductController extends CrudController<
        UUID,
        ProductDto,
        ProductCreateRequest,
        ProductCreateRequest> {

    public ProductController(ProductService service, ProductService productService) {
        super(service);
        this.productService = productService;
    }

    private final ProductService productService;

    @PublicEndpoint
    @Override
    @GetMapping("/search")
    public ApiResponse<PageResponse<ProductDto>> search(String keyword, Pageable pageable) {
        return super.search(keyword, pageable);
    }

    @PublicEndpoint
    @Override
    @GetMapping("/{id}")
    public ApiResponse<ProductDto> get(@PathVariable UUID id) {
        return super.get(id);
    }


    @PostMapping("/mainImage/{productId}/{attachmentId}")
    public ApiResponse<ProductDto> fillMainImageId(@PathVariable UUID productId, @PathVariable UUID attachmentId) {
        return ApiResponse.success(productService.fillMainImageId(productId, attachmentId));
    }
}