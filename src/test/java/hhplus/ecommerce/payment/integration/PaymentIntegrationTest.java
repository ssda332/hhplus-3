package hhplus.ecommerce.payment.integration;

import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.payment.application.PaymentFacade;
import hhplus.ecommerce.payment.domain.dto.PaymentDto;
import hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentIntegrationTest {

    @Autowired
    private PaymentFacade paymentFacade;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Test
    @DisplayName("동일 승인번호로 여러 결제 요청 시도")
    void testConcurrentPaymentRequests() throws InterruptedException {
        // given
        int numberOfThreads = 10;
        Long MEMBER_ID = 1L;
        Long ORDER_SHEET_ID = 1L;
        Long APPROVAL_NUMBER = UUID.randomUUID().getLeastSignificantBits();
        Long AMOUNT = 1L;

        CountDownLatch doneSignal = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();



        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                PaymentDto paymentDto = PaymentDto.builder()
                        .memberId(MEMBER_ID)
                        .orderSheetId(ORDER_SHEET_ID)
                        .approvalNumber(APPROVAL_NUMBER)
                        .amount(AMOUNT)
                        .build();

                try {
                    paymentFacade.savePayment(paymentDto);
                    successCount.getAndIncrement();
                } catch (RuntimeException e) {
                    failCount.getAndIncrement();
                } finally {
                    doneSignal.countDown();
                }
            });
        }

        doneSignal.await();
        executorService.shutdown();

        //then
        assertAll(
                () -> assertThat(successCount.get()).isEqualTo(1),
                () -> assertThat(failCount.get()).isEqualTo(9)
        );
    }
}