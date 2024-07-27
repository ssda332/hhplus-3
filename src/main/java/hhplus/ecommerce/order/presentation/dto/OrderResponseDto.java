package hhplus.ecommerce.order.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        Long id,
        Long memberId,
        Long priceSum,
        String address,
        String phone,
        String comment,
        LocalDateTime createDate,
        List<OrderItemResponseDto> orderItems
) {}