package hhplus.ecommerce.ordersheet.presentation.dto;

public record OrderSheetItemRequestDto(
        Long productId,
        String productName,
        Long productPrice,
        Long productOptionId,
        String productOptionName,
        Long productCount
) {
}