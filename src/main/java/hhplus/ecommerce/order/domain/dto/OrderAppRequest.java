package hhplus.ecommerce.order.domain.dto;

import lombok.Builder;

@Builder
public record OrderAppRequest(
        Long orderSheetId,
        Long memberId
) {

}
