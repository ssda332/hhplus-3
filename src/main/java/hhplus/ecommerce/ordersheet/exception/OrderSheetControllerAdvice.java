package hhplus.ecommerce.ordersheet.exception;

import hhplus.ecommerce.error.ErrorCode;
import hhplus.ecommerce.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderSheetControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = OrderSheetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderSheetNotFoundException(OrderSheetNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.ORDER_SHEET_NOT_FOUND,
                null
        );
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }


}