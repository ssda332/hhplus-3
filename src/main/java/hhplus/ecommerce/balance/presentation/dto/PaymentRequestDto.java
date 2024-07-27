package hhplus.ecommerce.balance.presentation.dto;

import lombok.Builder;

@Builder
public record PaymentRequestDto(
        Long orderSheetId,
        Long amount
) {
}
