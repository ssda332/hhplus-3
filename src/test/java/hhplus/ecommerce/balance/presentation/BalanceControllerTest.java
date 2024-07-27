package hhplus.ecommerce.balance.presentation;


import com.fasterxml.jackson.databind.ObjectMapper;
import hhplus.ecommerce.balance.domain.dto.BalanceCommand;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.service.BalanceService;
import hhplus.ecommerce.balance.presentation.dto.BalanceRequestDto;
import hhplus.ecommerce.balance.presentation.dto.BalanceResponseDto;
import hhplus.ecommerce.balance.mapper.BalanceMapper;
import hhplus.ecommerce.config.WebConfig;
import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import hhplus.ecommerce.member.interceptor.MemberInterceptor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BalanceController.class)
@AutoConfigureMockMvc(addFilters = false)
@MockBean(JpaMetamodelMappingContext.class)
public class BalanceControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Configuration
    static class TestConfig {
        @Bean
        public WebConfig webConfig(MemberInterceptor memberInterceptor) {
            return new WebConfig(memberInterceptor);
        }
    }


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BalanceService balanceService;

    @MockBean
    private BalanceMapper balanceMapper;

    @MockBean
    private MemberInterceptor memberInterceptor;

    @Test
    @DisplayName("잔액 조회 성공")
    void successFindBalance() throws Exception {
        //given
        String url = "/balance";
        Long memberId = 1L;
        Balance balance = Balance.builder()
                .memberId(1L)
                .amount(100L)
                .build();

        BalanceResponseDto dto = BalanceResponseDto.builder()
                .memberId(1L).amount(100L).build();

        //when
        given(balanceService.findBalance(any(Long.class))).willReturn(balance);
        given(balanceMapper.toDto(any(Balance.class))).willReturn(dto);

        ResultActions actions = mockMvc.perform(get(url)
                .param("memberId", memberId.toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(1L))
                .andExpect(jsonPath("$.amount").value(100L));

    }

    @Test
    @DisplayName("잔액 충전 성공")
    void successChargeBalance() throws Exception {
        //given
        String url = "/balance/charge";
        Long memberId = 1L;
        Long amount = 100L;

        Balance balance = Balance.builder()
                .memberId(memberId)
                .amount(150L)
                .build();

        BalanceResponseDto responseDto = BalanceResponseDto.builder()
                .memberId(memberId)
                .amount(250L)
                .build();

        // Mock 인터셉터 동작
        given(memberInterceptor.preHandle(any(), any(), any())).willReturn(true);

        //when
        given(balanceService.chargeBalance(any(BalanceCommand.Charge.class))).willReturn(balance);
        given(balanceMapper.toDto(any(Balance.class))).willReturn(responseDto);
        given(balanceMapper.toEntity(any(BalanceRequestDto.class))).willReturn(balance);

        ResultActions actions = mockMvc.perform(post(url)
                .header("MEMBER_ID", memberId)
                .param("amount", String.valueOf(amount))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").value(memberId))
                .andExpect(jsonPath("$.amount").value(250L));
    }

    @Test
    @DisplayName("잔액 조회 실패 - 멤버가 존재하지 않음")
    void failFindBalance() throws Exception {
        //given
        String url = "/balance";
        Long memberId = 1L;

        //when
        given(balanceService.findBalance(any(Long.class))).willThrow(new MemberNotFoundException("사용자가 존재하지 않습니다."));

        ResultActions actions = mockMvc.perform(get(url)
                .param("memberId", memberId.toString())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        //then
        actions.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("사용자가 존재하지 않습니다."));
    }


}
