package hhplus.ecommerce.payment.presentation.dto;

import lombok.Builder;

@Builder
public record PaymentRequestDto(
        Long memberId,
        Long orderSheetId,
        Long approvalNumber,
        double amount
) {
}