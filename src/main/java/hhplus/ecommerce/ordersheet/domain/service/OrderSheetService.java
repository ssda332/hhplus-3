package hhplus.ecommerce.ordersheet.domain.service;

import hhplus.ecommerce.ordersheet.domain.dto.OrderSheetDto;
import hhplus.ecommerce.ordersheet.domain.dto.OrderSheetItemDto;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.ordersheet.mapper.OrderSheetMapper;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSheetService {

    private final OrderSheetRepository orderSheetRepository;
    private final ProductOptionRepository productOptionRepository;
    private final OrderSheetMapper orderSheetMapper;

    @Transactional
    public OrderSheet createOrderSheet(OrderSheetDto orderSheetDto) {
        OrderSheet orderSheet = orderSheetMapper.toEntity(orderSheetDto);
        return orderSheetRepository.save(orderSheet);
    }

    public void checkOrderSheetItemList(List<OrderSheetItemDto> list) {
        list.stream().forEach(item -> {
            ProductOption productOption = productOptionRepository.findById(item.productOptionId())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            ProductOptionStock productOptionStock = productOption.getProductOptionStock();
            productOptionStock.checkStock();
        });
    }

    @Transactional(readOnly = true)
    public OrderSheet getOrderSheetById(Long id) {
        return orderSheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderSheet not found"));
    }
}