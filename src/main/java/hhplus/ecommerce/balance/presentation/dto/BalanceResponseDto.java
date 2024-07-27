package hhplus.ecommerce.balance.presentation.dto;

import lombok.Builder;

@Builder
public record BalanceResponseDto(
        Long memberId,
        Long amount
) {
}
