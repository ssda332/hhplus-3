package hhplus.ecommerce.common.exception;

import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import hhplus.ecommerce.error.ErrorCode;
import hhplus.ecommerce.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, null);
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

}
