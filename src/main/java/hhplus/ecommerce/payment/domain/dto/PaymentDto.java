package hhplus.ecommerce.payment.domain.dto;

import lombok.Builder;

@Builder
public record PaymentDto(
        Long memberId,
        Long orderSheetId,
        Long approvalNumber,
        Long amount
) {
}
