package hhplus.ecommerce.product.exception;

import hhplus.ecommerce.error.ErrorCode;
import hhplus.ecommerce.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ProductControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.PRODUCT_NOT_FOUND,
                null
        );
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }

    @ExceptionHandler(value = ProductOptionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductOptionNotFoundException(ProductOptionNotFoundException e) {
        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.PRODUCT_OPTION_NOT_FOUND,
                null
        );
        return ResponseEntity.status(errorResponse.status()).body(errorResponse);
    }


}