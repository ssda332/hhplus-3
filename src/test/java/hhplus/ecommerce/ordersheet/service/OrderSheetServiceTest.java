package hhplus.ecommerce.ordersheet.service;

import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.ordersheet.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.ordersheet.domain.service.OrderSheetService;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class OrderSheetServiceTest {

    @InjectMocks
    private OrderSheetService orderSheetService;

    @Mock
    private OrderSheetRepository orderSheetRepository;

    @Mock
    private ProductRepository productRepository;

    /*@Test
    @DisplayName("주문서 생성 성공")
    void testCreateOrderSheet_Success() {
        // given
        OrderSheet orderSheet = new OrderSheet();
        OrderSheetItem item = new OrderSheetItem();
        item.setProductId(1L);
        item.setProductOptionId(1L);
        item.setProductCount(5L);
        orderSheet.setOrderSheetItems(Collections.singletonList(item));

        Product product = new Product();
        product.setId(1L);
        ProductOption productOption = new ProductOption();
        productOption.setId(1L);
        ProductOptionStock productOptionStock = ProductOptionStock.builder()
                .stock(10L)
                .build();
        productOption.setProductOptionStock(productOptionStock);
        product.setProductOptions(Collections.singletonList(productOption));

        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        given(orderSheetRepository.save(orderSheet)).willReturn(orderSheet);

        // when
        OrderSheet result = orderSheetService.createOrderSheet(orderSheet);

        // then
        then(productRepository).should(times(1)).findById(1L);
        then(orderSheetRepository).should(times(1)).save(orderSheet);
    }*/

    /*@Test
    @DisplayName("주문서 생성 실패 - 재고 부족")
    void testCreateOrderSheet_InsufficientStock() {
        // given
        OrderSheet orderSheet = new OrderSheet();
        OrderSheetItem item = new OrderSheetItem();
        item.setProductId(1L);
        item.setProductOptionId(1L);
        item.setProductCount(15L);
        orderSheet.setOrderSheetItems(Collections.singletonList(item));

        Product product = new Product();
        product.setId(1L);
        ProductOption productOption = new ProductOption();
        productOption.setId(1L);
        //ProductOptionStock productOptionStock = new ProductOptionStock();
        //productOptionStock.setStock(10L);
        ProductOptionStock productOptionStock = ProductOptionStock.builder()
                .stock(10L)
                .build();
        productOption.setProductOptionStock(productOptionStock);
        product.setProductOptions(Collections.singletonList(productOption));

        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        // when
        // then
        assertThatThrownBy(() -> orderSheetService.createOrderSheet(orderSheet))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("재고 없음");

        then(productRepository).should(times(1)).findById(1L);
        then(orderSheetRepository).shouldHaveNoInteractions();
    }*/

    /*@Test
    @DisplayName("주문서 생성 실패 - 상품 없음")
    void testCreateOrderSheet_ProductNotFound() {
        // given
        OrderSheet orderSheet = new OrderSheet();
        OrderSheetItem item = new OrderSheetItem();
        item.setProductId(1L);
        item.setProductOptionId(1L);
        item.setProductCount(5L);
        orderSheet.setOrderSheetItems(Collections.singletonList(item));

        given(productRepository.findById(1L)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> orderSheetService.createOrderSheet(orderSheet))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("상품 없음");

        then(productRepository).should(times(1)).findById(1L);
        then(orderSheetRepository).shouldHaveNoInteractions();
    }*/

    @Test
    @DisplayName("주문서 조회 성공")
    void testGetOrderSheetById_Success() {
        // given
        Long orderSheetId = 1L;
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setId(orderSheetId);

        given(orderSheetRepository.findById(orderSheetId)).willReturn(Optional.of(orderSheet));

        // when
        OrderSheet result = orderSheetService.getOrderSheetById(orderSheetId);

        // then
        then(orderSheetRepository).should(times(1)).findById(orderSheetId);
        assertThat(result.getId()).isEqualTo(orderSheetId);
    }

    @Test
    @DisplayName("주문서 조회 실패 - 주문서 없음")
    void testGetOrderSheetById_NotFound() {
        // given
        Long orderSheetId = 1L;

        given(orderSheetRepository.findById(orderSheetId)).willReturn(Optional.empty());

        // when
        // then
        assertThatThrownBy(() -> orderSheetService.getOrderSheetById(orderSheetId))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("OrderSheet not found");

        then(orderSheetRepository).should(times(1)).findById(orderSheetId);
    }
}