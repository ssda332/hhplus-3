package hhplus.ecommerce.order.service;

import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.balance.exception.InsufficientBalanceException;
import hhplus.ecommerce.order.domain.dto.OrderAppRequest;
import hhplus.ecommerce.order.domain.entity.Order;
import hhplus.ecommerce.order.domain.service.OrderService;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.order.domain.repository.OrderRepository;
import hhplus.ecommerce.ordersheet.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.order.mapper.OrderMapper;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderSheetRepository orderSheetRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @Mock
    private OrderMapper orderMapper;

    private OrderAppRequest appRequest;
    private OrderSheet orderSheet;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setUp() {
        appRequest = new OrderAppRequest(1L, 1L);
        orderSheet = OrderSheet.builder()
                .memberId(1L)
                .orderSheetItems(Arrays.asList(
                        OrderSheetItem.builder()
                                .productId(1L)
                                .productOptionId(1L)
                                .productCount(2L)
                                .productPrice(100L)
                                .build(),
                        OrderSheetItem.builder()
                                .productId(2L)
                                .productOptionId(2L)
                                .productCount(3L)
                                .productPrice(200L)
                                .build(),
                        OrderSheetItem.builder()
                                .productId(3L)
                                .productOptionId(3L)
                                .productCount(1L)
                                .productPrice(300L)
                                .build()
                ))
                .build();

        product1 = createProduct(1L, 1L, 10L);
        product2 = createProduct(2L, 2L, 10L);
        product3 = createProduct(3L, 3L, 10L);
    }

    private Product createProduct(Long productId, Long productOptionId, Long stock) {
        ProductOptionStock productOptionStock = ProductOptionStock.builder()
                .id(productOptionId)
                .stock(stock)
                .build();

        ProductOption productOption = ProductOption.builder()
                .id(productOptionId)
                .productOptionStock(productOptionStock)
                .build();

        return Product.builder()
                .id(productId)
                .productOptions(Collections.singletonList(productOption))
                .build();
    }

    @Test
    @DisplayName("주문 생성 성공")
    void createOrder_Success() throws InsufficientBalanceException {
        // given
        Balance balance = Balance.builder().memberId(1L).amount(10000L).build();

        Order order = new Order();
        order.setMemberId(1L);

        given(orderSheetRepository.findById(1L)).willReturn(Optional.of(orderSheet));
        given(productRepository.findById(1L)).willReturn(Optional.of(product1));
        given(productRepository.findById(2L)).willReturn(Optional.of(product2));
        given(productRepository.findById(3L)).willReturn(Optional.of(product3));
        given(balanceRepository.findByMemberId(1L)).willReturn(Optional.of(balance));
        given(orderMapper.toEntity(any(OrderSheet.class), any(Long.class))).willReturn(order);
        given(orderRepository.save(any(Order.class))).willReturn(order);

        // when
        orderService.createOrder(appRequest);

        // then
        then(orderSheetRepository).should(times(1)).findById(1L);
        then(productRepository).should(times(3)).findById(any(Long.class));
        then(balanceRepository).should(times(1)).findByMemberId(1L);
        then(orderMapper).should(times(1)).toEntity(any(OrderSheet.class), any(Long.class));
        then(orderRepository).should(times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("주문 생성 실패 - 주문서 없음")
    void createOrder_OrderSheetNotFound() {
        // given
        given(orderSheetRepository.findById(1L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> orderService.createOrder(appRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("주문서 없음");

        then(orderSheetRepository).should(times(1)).findById(1L);
        then(productRepository).shouldHaveNoInteractions();
        then(balanceRepository).shouldHaveNoInteractions();
        then(orderMapper).shouldHaveNoInteractions();
        then(orderRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("주문 생성 실패 - 상품 없음")
    void createOrder_ProductNotFound() {
        // given
        given(orderSheetRepository.findById(1L)).willReturn(Optional.of(orderSheet));
        given(productRepository.findById(any())).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> orderService.createOrder(appRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("상품 없음");

        then(orderSheetRepository).should(times(1)).findById(1L);
        then(productRepository).should(times(1)).findById(any(Long.class));
        then(balanceRepository).shouldHaveNoInteractions();
        then(orderMapper).shouldHaveNoInteractions();
        then(orderRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("주문 생성 실패 - 재고 없음")
    void createOrder_InsufficientStock() {
        // given
        orderSheet.getOrderSheetItems().get(0).setProductCount(20L); // 재고보다 많은 수량

        given(orderSheetRepository.findById(1L)).willReturn(Optional.of(orderSheet));
        given(productRepository.findById(any())).willReturn(Optional.of(product1));

        // when & then
        assertThatThrownBy(() -> orderService.createOrder(appRequest))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("재고 없음");

        then(orderSheetRepository).should(times(1)).findById(1L);
        then(productRepository).should(times(1)).findById(any(Long.class));
        then(balanceRepository).shouldHaveNoInteractions();
        then(orderMapper).shouldHaveNoInteractions();
        then(orderRepository).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("주문 생성 실패 - 잔액 없음")
    void createOrder_InsufficientBalance() {
        // given
        Balance balance = Balance.builder().memberId(1L).amount(10L).build(); // 잔액 10원

        given(orderSheetRepository.findById(1L)).willReturn(Optional.of(orderSheet));
        given(productRepository.findById(any())).willReturn(Optional.of(product1));
        given(balanceRepository.findByMemberId(1L)).willReturn(Optional.of(balance));

        // when & then
        assertThatThrownBy(() -> orderService.createOrder(appRequest))
                .isInstanceOf(InsufficientBalanceException.class);
                //.hasMessage("잔액이 부족합다.");

        then(orderSheetRepository).should(times(1)).findById(1L);
        then(productRepository).should(times(3)).findById(any(Long.class));
        then(balanceRepository).should(times(1)).findByMemberId(1L);
        then(orderMapper).shouldHaveNoInteractions();
        then(orderRepository).shouldHaveNoInteractions();
    }
}