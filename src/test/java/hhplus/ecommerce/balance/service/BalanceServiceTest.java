package hhplus.ecommerce.balance.service;

import hhplus.ecommerce.balance.domain.dto.BalanceCommand;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.balance.domain.service.BalanceService;
import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceTest {

    @InjectMocks
    private BalanceService balanceService;

    @Mock
    private BalanceRepository balanceRepository;

    @Test
    @DisplayName("특정 멤버ID의 잔액 조회 - 성공")
    void findBalanceByMemberId() throws Exception {
        //given
        Long memberId = 1L;
        Balance balance = Balance.builder()
                .id(1L)
                .memberId(1L)
                .amount(100L)
                .build();

        given(balanceRepository.findByMemberId(memberId)).willReturn(Optional.of(balance));

        //when
        Balance result = balanceService.findBalance(memberId);

        //then
        assertThat(result.getMemberId()).isEqualTo(balance.getMemberId());
        assertThat(result.getAmount()).isEqualTo(balance.getAmount());
    }

    @Test
    @DisplayName("특정 멤버ID의 잔액 조회 - 실패 (멤버 존재하지 않음)")
    void findBalanceByMemberIdFail() throws Exception {
        //given
        Long memberId = 1L;

        given(balanceRepository.findByMemberId(memberId)).willThrow(new MemberNotFoundException("사용자가 존재하지 않습니다."));

        //when
        //then
        assertThatThrownBy(() -> balanceService.findBalance(memberId))
                .isInstanceOf(MemberNotFoundException.class)
                .hasMessage("사용자가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("잔액 충전 - 성공")
    void chargeBalanceSuccess() throws Exception {
        //given
        Long chargeAmount = 150L;
        Balance balance = Balance.builder()
                .id(1L)
                .amount(100L)
                .build();

        //when
        Balance result = balanceService.chargeBalance(new BalanceCommand.Charge(chargeAmount, balance));

        //then
        assertThat(result.getMemberId()).isEqualTo(result.getMemberId());
        assertThat(result.getAmount()).isEqualTo(balance.getAmount());
    }

    /*@Test
    @DisplayName("잔액 충전 - 실패 (멤버 존재하지 않음)")
    void chargeBalanceFail() throws Exception {
        //given
        Long chargeAmount = 150L;
        Balance balance = Balance.builder()
            .id(1L)
            .amount(100L)
            .build();

        //when
        //then
        assertThatThrownBy(() -> balanceService.chargeBalance(new BalanceCommand.Charge(chargeAmount, balance)))
                .isInstanceOf(MemberNotFoundException.class)
                .hasMessage("사용자가 존재하지 않습니다.");
    }*/

}
