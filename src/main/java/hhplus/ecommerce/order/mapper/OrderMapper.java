package hhplus.ecommerce.order.mapper;

import hhplus.ecommerce.order.presentation.dto.*;
import hhplus.ecommerce.order.domain.dto.OrderAppRequest;
import hhplus.ecommerce.order.domain.entity.Order;
import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponseDto toDto(Order order);
    OrderAppRequest toAppDto(OrderRequestDto orderRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "priceSum", source = "sumPrice")
    Order toEntity(OrderSheet orderSheet, Long sumPrice);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "order", source = "order")
    OrderItem toOrderItem(OrderSheetItem orderSheetItem, Order order);

    List<OrderItemResponseDto> toOrderItemDtos(List<OrderItem> orderItems);

    default OrderResponseDto toDtoWithItems(Order order) {
        return new OrderResponseDto(
                order.getId(),
                order.getMemberId(),
                order.getPriceSum(),
                order.getAddress(),
                order.getPhone(),
                order.getComment(),
                order.getCreateDate(),
                toOrderItemDtos(order.getOrderItems())
        );
    }
}