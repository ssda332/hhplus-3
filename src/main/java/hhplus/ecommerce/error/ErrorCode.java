package hhplus.ecommerce.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("CM_000", "서버 에러", 500),
    MEMBER_NOT_FOUND("CM_001", "사용자를 찾을 수 없습니다.", 404),
    INSUFFICIENT_BALANCE("CM_002", "잔액이 부족합니다.", 400),

    PRODUCT_NOT_FOUND("PR_001", "상품을 찾을 수 없습니다.", 404),
    PRODUCT_OPTION_NOT_FOUND("PR_001", "상품 옵션을 찾을 수 없습니다.", 404),
    PRODUCT_OPTION_STOCK_NOT_FOUND("PR_001", "재고 정보를 찾을 수 없습니다.", 404),

    ORDER_SHEET_NOT_FOUND("OS_001", "주문서를 찾을 수 없습니다.", 404);

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(String code, String message, int status){
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
