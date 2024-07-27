package hhplus.ecommerce.balance.infrastructure;

import lombok.Builder;

@Builder
public record PaymentInfraRequestDto(
        Long userId,
        Long orderSheetId,
        Long amount
) {
}
