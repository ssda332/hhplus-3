package hhplus.ecommerce.error;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public record ErrorResponse(
        String message,
        String code,
        int status,
        List<FieldError> errors
) {

    public ErrorResponse {
        errors = errors != null ? List.copyOf(errors) : new ArrayList<>();
    }

    public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
        return ErrorResponse.builder()
                .code(errorCode.getCode())
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .errors(errors)
                .build();
    }

    public static record FieldError(
            String field,
            String value,
            String reason
    ) {
        public static FieldError of(String field, String value, String reason) {
            return new FieldError(field, value, reason);
        }
    }
}