package hhplus.ecommerce.order.exception;

import hhplus.ecommerce.error.ErrorCode;
import hhplus.ecommerce.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderControllerAdvice extends ResponseEntityExceptionHandler {



}