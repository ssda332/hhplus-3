package hhplus.ecommerce.ordersheet.exception;

public class OrderSheetNotFoundException extends RuntimeException {
    public OrderSheetNotFoundException(String message) {
        super(message);
    }
}