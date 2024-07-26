package hhplus.ecommerce.order.application;

import hhplus.ecommerce.balance.exception.InsufficientBalanceException;
import hhplus.ecommerce.order.domain.dto.OrderAppRequest;
import hhplus.ecommerce.order.domain.entity.Order;
import hhplus.ecommerce.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;

    public Order createOrder(OrderAppRequest appRequest) throws InsufficientBalanceException {
        return orderService.createOrder(appRequest);
    }
}