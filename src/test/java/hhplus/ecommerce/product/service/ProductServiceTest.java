package hhplus.ecommerce.product.service;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.order.domain.repository.OrderItemRepository;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import hhplus.ecommerce.product.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    private Product product;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(1000L)
                .build();

        products = Arrays.asList(
                Product.builder().id(1L).name("Product 1").price(100L).build(),
                Product.builder().id(2L).name("Product 2").price(200L).build(),
                Product.builder().id(3L).name("Product 3").price(300L).build(),
                Product.builder().id(4L).name("Product 4").price(400L).build(),
                Product.builder().id(5L).name("Product 5").price(500L).build()
        );

    }

    @Test
    @DisplayName("상품 조회 - 성공")
    void getProductByIdSuccess() {
        // given
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        // when
        Product foundProduct = productService.getProductById(1L);

        // then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(product.getId());
        assertThat(foundProduct.getName()).isEqualTo(product.getName());
        assertThat(foundProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    @DisplayName("상품 조회 - 실패 (존재하지 않는 ID)")
    void getProductByIdFail() {
        // given
        given(productRepository.findById(1L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.getProductById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Product not found");
    }

    @Test
    @DisplayName("상위 상품 조회 - 성공")
    void getTopProductsSuccess() {
        // given
        List<Long> topProductIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        given(orderItemRepository.findTopProductIds(PageRequest.of(0, 5))).willReturn(topProductIds);
        given(productRepository.findById(1L)).willReturn(Optional.of(products.get(0)));
        given(productRepository.findById(2L)).willReturn(Optional.of(products.get(1)));
        given(productRepository.findById(3L)).willReturn(Optional.of(products.get(2)));
        given(productRepository.findById(4L)).willReturn(Optional.of(products.get(3)));
        given(productRepository.findById(5L)).willReturn(Optional.of(products.get(4)));

        // when
        List<Product> topProducts = productService.getTopProducts();

        // then
        assertThat(topProducts).isNotNull();
        assertThat(topProducts.size()).isEqualTo(5);

        // 상위 5개의 아이템을 예상 순서대로 정렬
        List<Long> expectedProductIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        for (int i = 0; i < 5; i++) {
            assertThat(topProducts.get(i).getId()).isEqualTo(expectedProductIds.get(i));
        }
    }
}