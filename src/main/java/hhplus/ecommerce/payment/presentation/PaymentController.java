package hhplus.ecommerce.payment.presentation;

import hhplus.ecommerce.payment.application.PaymentFacade;
import hhplus.ecommerce.payment.domain.entity.Payment;
import hhplus.ecommerce.payment.mapper.PaymentMapper;
import hhplus.ecommerce.payment.presentation.dto.PaymentRequestDto;
import hhplus.ecommerce.payment.presentation.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentFacade paymentFacade;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> savePayment(@RequestBody PaymentRequestDto paymentRequestDto) {
        PaymentResponseDto responseDto = paymentMapper.toResponseDto(paymentFacade.savePayment(paymentMapper.toDto(paymentRequestDto)));
        return ResponseEntity.ok(responseDto);
    }


}