package hhplus.ecommerce.balance.infrastructure;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentInfraResponseDto(
        Long paymentId,
        Long userId,
        Long orderId,
        Long amount,
        String status,
        LocalDateTime paymentDate
) {

}
