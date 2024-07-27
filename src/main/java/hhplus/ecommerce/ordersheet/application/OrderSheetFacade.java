package hhplus.ecommerce.ordersheet.application;

import hhplus.ecommerce.ordersheet.domain.dto.OrderSheetDto;
import hhplus.ecommerce.ordersheet.domain.dto.OrderSheetItemDto;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.service.OrderSheetService;
import hhplus.ecommerce.product.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderSheetFacade {

    private final OrderSheetService orderSheetService;
    private final ProductService productService;

    public OrderSheet createOrderSheet(OrderSheetDto orderSheetDto) {
        List<OrderSheetItemDto> list = orderSheetDto.items();
        orderSheetService.checkOrderSheetItemList(list);
        OrderSheet orderSheet = orderSheetService.createOrderSheet(orderSheetDto);

        return orderSheet;
    }

    public OrderSheet getOrderSheetById(Long id) {
        return orderSheetService.getOrderSheetById(id);
        //orderSheetService.createOrderSheet(orderS)
    }

}
