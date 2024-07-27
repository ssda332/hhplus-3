package hhplus.ecommerce.balance.domain.dto;

import lombok.Builder;

@Builder
public record BalanceDto(
        Long memberId,
        String approvalNumber,
        Long amount
) {
}
