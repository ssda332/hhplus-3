package hhplus.ecommerce.payment.presentation.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PaymentResponseDto(
        Long id,
        Long memberId,
        Long orderSheetId,
        Long orderId,
        String approvalNumber,
        double amount,
        LocalDateTime createDate
) {
}