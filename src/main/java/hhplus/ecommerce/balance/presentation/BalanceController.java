package hhplus.ecommerce.balance.presentation;

import hhplus.ecommerce.balance.application.BalanceFacade;
import hhplus.ecommerce.balance.domain.dto.BalanceDto;
import hhplus.ecommerce.balance.presentation.dto.BalanceResponseDto;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.mapper.BalanceMapper;
import hhplus.ecommerce.balance.domain.service.BalanceService;
import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import hhplus.ecommerce.balance.presentation.dto.PaymentRequestDto;
import hhplus.ecommerce.balance.presentation.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceMapper balanceMapper;
    private final BalanceFacade balanceFacade;

    // 잔액 충전 API
    @PostMapping("/charge")
    public BalanceResponseDto chargeBalance(@RequestHeader("MEMBER_ID") Long memberId, @RequestParam Long amount) throws MemberNotFoundException {
        BalanceDto balance = BalanceDto.builder()
                .memberId(memberId)
                .amount(amount)
                .build();
        return balanceMapper.toDto(balanceFacade.chargeBalance(balance));
    }

    // 잔액 조회 API
    @GetMapping("")
    public BalanceResponseDto getBalance(@RequestHeader("MEMBER_ID") Long memberId) throws MemberNotFoundException {
        return balanceMapper.toDto(balanceFacade.getBalance(memberId));
    }

    /*// 결제 API
    @PostMapping("/payment")
    public PaymentResponseDto payment(@RequestHeader("MEMBER_ID") Long memberId, @RequestBody PaymentRequestDto paymentRequest) {
        Long paymentId = 1L;
        String status = "SUCCESS";
        LocalDateTime paymentDate = LocalDateTime.now();

        return new PaymentResponseDto(paymentId, memberId, paymentRequest.orderSheetId(),
                paymentRequest.amount(), status, paymentDate);
    }*/
}
