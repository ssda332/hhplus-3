package hhplus.ecommerce.product.controller;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.service.ProductService;
import hhplus.ecommerce.product.dto.ProductDetailResponseDto;
import hhplus.ecommerce.product.dto.ProductPopularDto;
import hhplus.ecommerce.product.dto.ProductResponseDto;
import hhplus.ecommerce.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    // 상위 상품 조회 API
    @GetMapping("/top")
    public List<ProductDetailResponseDto> getTopProducts() {
        List<ProductPopularDto> topProducts = productService.getTopProducts();
        return productMapper.toProductDetailResponseDtoList(topProducts);
    }

    // 상품 정보 조회 API
    @GetMapping("/{id}")
    public ProductResponseDto getProductDetail(@PathVariable("id") Long id) {
        return productMapper.toProductResponseDto(productService.getProductById(id));
    }

}
