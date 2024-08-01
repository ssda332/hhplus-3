package hhplus.ecommerce.order.integration;

import hhplus.ecommerce.balance.exception.InsufficientBalanceException;
import hhplus.ecommerce.order.application.OrderFacade;
import hhplus.ecommerce.order.domain.dto.OrderAppRequest;
import hhplus.ecommerce.order.domain.entity.Order;
import hhplus.ecommerce.order.domain.repository.OrderRepository;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductOptionStockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class OrderIntegrationTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductOptionStockRepository productOptionStockRepository;

    public ProductOptionStock stock1;

    @BeforeEach
    void setUp() {
        stock1 = productOptionStockRepository.findByProductOptionId(1L).orElseThrow(() -> new RuntimeException());

    }

    @Test
    @DisplayName("하나의 상품옵션에 대해 재고에 딱 맞춰서 여러 요청이 들어오고 요청들이 정상적으로 처리된다")
    void testConcurrentOrders_ideal() throws InterruptedException {
        // given
        int numberOfThreads = 10;
        stock1.changeStock(100L);
        List<Long> orderSheetList = new ArrayList<>();
        List<Long> memberIdList = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            orderSheetList.add(i, Long.valueOf(i + 1));
            memberIdList.add(i, Long.valueOf(i + 1));
        }

        CountDownLatch doneSignal = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();

        for (int i = 0; i < numberOfThreads; i++) {
            final Long orderSheetId = orderSheetList.get(i);
            final Long memberId = memberIdList.get(i);

            executorService.submit(() -> {
                OrderAppRequest appRequest = OrderAppRequest.builder()
                        .orderSheetId(orderSheetId)
                        .memberId(memberId)
                        .build();

                try {
                    orderFacade.createOrder(appRequest);
                    successCount.getAndIncrement();
                } catch (Exception e) {
                    failCount.getAndIncrement();
                } catch (InsufficientBalanceException e) {
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
                () -> assertThat(successCount.get()).isEqualTo(10),
                () -> assertThat(failCount.get()).isEqualTo(0)
        );

        // 재고 차감에 대한 11건 12건 해서 경계값

    }

    @Test
    @DisplayName("")
    void testConcurrentOrders_fail() throws InterruptedException {
        //given
        int numberOfThreads = 10;
        stock1.changeStock(100L);


    }

}