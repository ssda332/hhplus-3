package hhplus.ecommerce.balance.exception;

import hhplus.ecommerce.error.ErrorCode;
import hhplus.ecommerce.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BalanceControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMemberNotFoundException(MemberNotFoundException e) {
        ErrorCode errorCode = ErrorCode.MEMBER_NOT_FOUND;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, null);
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);

    }

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientBalanceException(InsufficientBalanceException e) {
        ErrorCode errorCode = ErrorCode.MEMBER_NOT_FOUND;
        ErrorResponse errorResponse = ErrorResponse.of(errorCode, null);
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);

    }


}