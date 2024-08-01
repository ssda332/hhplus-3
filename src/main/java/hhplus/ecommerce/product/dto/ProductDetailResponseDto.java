package hhplus.ecommerce.product.dto;

import java.time.LocalDateTime;

public record ProductDetailResponseDto(
    Long id,
    String name,
    Long optionId,
    String optionName,
    Long price,
    Long stock

) {
}
