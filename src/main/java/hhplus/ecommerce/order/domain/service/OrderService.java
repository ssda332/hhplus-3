package hhplus.ecommerce.order.domain.service;

import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.balance.exception.InsufficientBalanceException;
import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import hhplus.ecommerce.order.domain.dto.OrderAppRequest;
import hhplus.ecommerce.order.domain.entity.Order;
import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.order.domain.repository.OrderItemRepository;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.order.domain.repository.OrderRepository;
import hhplus.ecommerce.ordersheet.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.order.mapper.OrderMapper;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductOptionStockRepository;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import hhplus.ecommerce.product.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private final OrderSheetRepository orderSheetRepository;
    private final ProductOptionStockRepository productOptionStockRepository;
    private final BalanceRepository balanceRepository;
    private final OrderMapper orderMapper;


    @Transactional
    public Order createOrder(OrderAppRequest appRequest) throws InsufficientBalanceException {
        // 유효한 주문서 찾기
        OrderSheet orderSheet = orderSheetRepository.findById(appRequest.memberId())
                .orElseThrow(() -> new RuntimeException("주문서 없음"));

        // 재고확인
        List<OrderSheetItem> orderSheetItems = orderSheet.getOrderSheetItems();
        Long sumPrice = 0L;

        for (OrderSheetItem item : orderSheetItems) {
            ProductOptionStock stock = productOptionStockRepository.findByProductOptionIdForUpdate(item.getProductOptionId())
                    .orElseThrow(() -> new ProductNotFoundException("상품 없음"));

            stock.decreaseStock(item);

            // 차감금액 계산
            Long calculatePrice = item.getProductCount() * item.getProductPrice();
            sumPrice += calculatePrice;
        }

        // 잔액확인
        Balance balance = balanceRepository.findByMemberId(orderSheet.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException("멤버 없음"));

        // 잔액차감
        balance.decreaseAmount(sumPrice);

        // Order 생성
        Order order = orderMapper.toEntity(orderSheet, sumPrice);

        // OrderItem 생성
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderSheetItem orderSheetItem : orderSheetItems) {
            OrderItem orderItem = orderMapper.toOrderItem(orderSheetItem, order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return savedOrder;
    }

}