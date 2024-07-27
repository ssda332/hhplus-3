package hhplus.ecommerce.payment.mapper;

import hhplus.ecommerce.payment.domain.dto.PaymentDto;
import hhplus.ecommerce.payment.presentation.dto.PaymentRequestDto;
import hhplus.ecommerce.payment.presentation.dto.PaymentResponseDto;
import hhplus.ecommerce.payment.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "approvalNumber", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    Payment toEntity(PaymentRequestDto dto);

    PaymentDto toDto(PaymentRequestDto dto);

    PaymentResponseDto toResponseDto(Payment payment);
}