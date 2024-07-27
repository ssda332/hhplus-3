package hhplus.ecommerce.balance.domain.service;

import hhplus.ecommerce.balance.domain.dto.BalanceCommand;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.balance.infrastructure.ExternalPaymentStub;
import hhplus.ecommerce.balance.infrastructure.PaymentInfraResponseDto;
import hhplus.ecommerce.balance.mapper.BalanceMapper;
import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Balance findBalance(Long memberId) throws MemberNotFoundException {
        return balanceRepository.findByMemberId(memberId).orElseThrow(() -> new MemberNotFoundException("사용자가 존재하지 않습니다."));
    }

    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public Balance chargeBalance(BalanceCommand.Charge command) {
        Balance balance = command.balance();
        balance.chargeAmount(command.amount());
        return balance;
    }

}
