package hhplus.ecommerce.product.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDto(
        Long id,
        String name,
        Long price,
        LocalDateTime createDate,
        List<ProductOptionDto> productOptionDtos

) {
}
