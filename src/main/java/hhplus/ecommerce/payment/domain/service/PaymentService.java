package hhplus.ecommerce.payment.domain.service;

import hhplus.ecommerce.payment.domain.dto.PaymentDto;
import hhplus.ecommerce.payment.exception.DuplicatePaymentException;
import hhplus.ecommerce.payment.presentation.dto.PaymentRequestDto;
import hhplus.ecommerce.payment.presentation.dto.PaymentResponseDto;
import hhplus.ecommerce.payment.domain.entity.Payment;
import hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import hhplus.ecommerce.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Payment savePayment(PaymentDto paymentDto) {
        // 새로운 결제 엔티티 생성
        Payment payment = Payment.builder()
                .memberId(paymentDto.memberId())
                .orderSheetId(paymentDto.orderSheetId())
                .orderId(UUID.randomUUID().getLeastSignificantBits()) // 실제 주문 ID 설정 (예시)
                .approvalNumber(paymentDto.approvalNumber())
                .amount(paymentDto.amount())
                .createDate(LocalDateTime.now())
                .build();

        // 결제 엔티티 저장
        Payment savedPayment = paymentRepository.save(payment);

        // 저장된 결제 엔티티를 응답 DTO로 변환하여 반환
        return savedPayment;
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void checkPaymentInfo(Long approvalNumber) {
        Optional<Payment> existingPayment = paymentRepository.findByApprovalNumberForUpdate(approvalNumber);

        if (existingPayment.isPresent()) {
            // 이미 존재하는 경우 중복 결제 처리 로직
            throw new RuntimeException("중복 결제 승인 번호입니다.");
        }
    }
}