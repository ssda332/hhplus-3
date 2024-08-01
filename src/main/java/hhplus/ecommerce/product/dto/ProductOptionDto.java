package hhplus.ecommerce.product.dto;

import hhplus.ecommerce.product.domain.entity.ProductOptionStock;

import java.time.LocalDateTime;

public record ProductOptionDto(
        Long id,
        String name,
        LocalDateTime createDate,
        ProductOptionStockDto productOptionStockDto
) {
}
