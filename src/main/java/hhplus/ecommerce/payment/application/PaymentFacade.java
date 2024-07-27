package hhplus.ecommerce.payment.application;

import hhplus.ecommerce.balance.domain.dto.BalanceCommand;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.service.BalanceService;
import hhplus.ecommerce.payment.domain.dto.PaymentDto;
import hhplus.ecommerce.payment.domain.entity.Payment;
import hhplus.ecommerce.payment.domain.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@RequiredArgsConstructor
public class PaymentFacade {

    private final PaymentService paymentService;
    private final BalanceService balanceService;

    @Transactional
    public Payment savePayment(PaymentDto dto) {
        // 결제정보 저장
        paymentService.checkPaymentInfo(dto.approvalNumber());
        Payment payment = paymentService.savePayment(dto);

        // 잔액 충전
        Balance balance = balanceService.findBalance(dto.memberId());
        balanceService.chargeBalance(new BalanceCommand.Charge(dto.amount(), balance));

        return payment;
    }

}
