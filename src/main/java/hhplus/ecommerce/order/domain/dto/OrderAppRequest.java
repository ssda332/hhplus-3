package hhplus.ecommerce.order.domain.dto;

public record OrderAppRequest(
        Long orderSheetId,
        Long memberId
) {

}
