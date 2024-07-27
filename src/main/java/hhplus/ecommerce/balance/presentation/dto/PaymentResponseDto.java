package hhplus.ecommerce.balance.presentation.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentResponseDto(
        Long paymentId,
        Long userId,
        Long orderId,
        Long amount,
        String status,
        LocalDateTime paymentDate
) {

}
