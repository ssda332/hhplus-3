package hhplus.ecommerce.product.dto;

import lombok.Builder;

@Builder
public record ProductPopularDto(
        Long id,
        String name,
        Long optionId,
        String optionName,
        Long price,
        Long stock
) {
}
