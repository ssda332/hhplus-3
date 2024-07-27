package hhplus.ecommerce.product.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductResponseDto(
        Long id,
        String name,
        Long price,
        LocalDateTime createDate,
        List<ProductDetailResponseDto> productDetails
) {
}