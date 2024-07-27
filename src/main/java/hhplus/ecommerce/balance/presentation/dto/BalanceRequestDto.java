package hhplus.ecommerce.balance.presentation.dto;

import lombok.Builder;

@Builder
public record BalanceRequestDto(
        Long memberId,
        Long amount
) {
}
