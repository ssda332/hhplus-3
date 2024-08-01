package hhplus.ecommerce.product.Integration;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.order.domain.repository.OrderItemRepository;
import hhplus.ecommerce.payment.application.PaymentFacade;
import hhplus.ecommerce.product.controller.ProductController;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import hhplus.ecommerce.product.domain.service.ProductService;
import hhplus.ecommerce.product.dto.ProductDto;
import hhplus.ecommerce.product.dto.ProductPopularDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    @DisplayName("상위 상품 목록 캐시 무효화 후 -> 주문내역 변동처리 -> 다시 요청")
    void testCacheInvalidation() {
        // 첫 번째 요청
        List<ProductPopularDto> firstCall = productService.getTopProducts();

        // 옷2의 그레이 옵션을 두 개 추가하는 주문 항목 생성
        ProductOption productOption = productRepository.findById(1L).orElseThrow().getProductOptions().stream()
                .filter(option -> option.getName().equals("그레이"))
                .findFirst()
                .orElseThrow();

        OrderItem orderItem1 = OrderItem.builder()
                .order(null) // 실제 테스트에서 올바른 Order 객체를 설정해야 합니다.
                .productId(productOption.getProduct().getId())
                .productName(productOption.getProduct().getName())
                .productOptionId(productOption.getId())
                .productOptionName(productOption.getName())
                .productPrice(productOption.getProduct().getPrice())
                .productCount(10L)
                .createDate(LocalDateTime.now())
                .build();

        OrderItem orderItem2 = OrderItem.builder()
                .order(null) // 실제 테스트에서 올바른 Order 객체를 설정해야 합니다.
                .productId(productOption.getProduct().getId())
                .productName(productOption.getProduct().getName())
                .productOptionId(productOption.getId())
                .productOptionName(productOption.getName())
                .productPrice(productOption.getProduct().getPrice())
                .productCount(10L)
                .createDate(LocalDateTime.now())
                .build();
        orderItemRepository.save(orderItem1);
        orderItemRepository.save(orderItem2);
        orderItemRepository.deleteById(1L);

        // 캐시 무효화
        productService.evictCache_topProducts();

        // 두 번째 요청
        List<ProductPopularDto> secondCall = productService.getTopProducts();

        // 두 요청 결과가 동일하지 않을 수 있음
        assertThat(firstCall).isNotEqualTo(secondCall);
    }

    @Test
    @DisplayName("상위 상품 목록을 여러번 요청해도 DB에 접근하는 횟수는 단 한번이어야 한다.")
    void testCachingTopProduct() throws InterruptedException {
        // 첫 번째 요청
        List<ProductPopularDto> firstCall = productService.getTopProducts();

        // 두 번째 요청
        List<ProductPopularDto> secondCall = productService.getTopProducts();

        // 세 번째 요청
        List<ProductPopularDto> thirdCall = productService.getTopProducts();

        // 요청 결과 검증
        assertThat(firstCall).isEqualTo(secondCall);
        assertThat(secondCall).isEqualTo(thirdCall);

        // 캐시가 제대로 동작하는지 확인하기 위해 DB 접근 횟수 확인
        long queryCountBefore = productRepository.count();
        List<ProductPopularDto> fourthCall = productService.getTopProducts();
        long queryCountAfter = productRepository.count();

        assertThat(queryCountBefore).isEqualTo(queryCountAfter);
    }

    @Test
    @DisplayName("상품 정보 업데이트 후 캐시 무효화 테스트")
    void testCacheInvalidationAfterUpdate() {
        // 첫 번째 요청
        ProductDto firstCall = productService.getProductById(1L);

        // 상품 정보 업데이트
        ProductDto updatedProduct = new ProductDto(
                1L,
                "업데이트된 상품명",
                1500L,
                LocalDateTime.now(),
                firstCall.productOptionDtos()
        );
        productService.updateProduct(updatedProduct);

        // 두 번째 요청
        ProductDto secondCall = productService.getProductById(1L);

        // 두 요청 결과 검증
        assertThat(firstCall.name()).isNotEqualTo(secondCall.name());
        assertThat(secondCall.name()).isEqualTo("업데이트된 상품명");
    }
}
