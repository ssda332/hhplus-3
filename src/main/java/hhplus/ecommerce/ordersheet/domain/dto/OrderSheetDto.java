package hhplus.ecommerce.ordersheet.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderSheetDto(
        Long memberId,
        String address,
        String phone,
        String comment,
        List<OrderSheetItemDto> items
) {
}
