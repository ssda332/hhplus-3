package hhplus.ecommerce.product.exception;

public class ProductOptionNotFoundException extends RuntimeException {
    public ProductOptionNotFoundException(String message) {
        super(message);
    }
}