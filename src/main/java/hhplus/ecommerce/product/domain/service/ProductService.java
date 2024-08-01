package hhplus.ecommerce.product.domain.service;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.order.domain.repository.OrderItemRepository;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductOptionRepository;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import hhplus.ecommerce.product.dto.ProductDto;
import hhplus.ecommerce.product.dto.ProductPopularDto;
import hhplus.ecommerce.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "product", key = "20", cacheManager = "productCacheManager")
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDto productDto = productMapper.toProductDto(product);

        return productDto;
    }

    @Transactional
    public ProductOptionStock getProductOptionStock(Long productOptionId) {
        ProductOption productOption = productOptionRepository.findById(productOptionId)
                .orElseThrow(() -> new RuntimeException("상품옵션이 없습니다."));

        ProductOptionStock productOptionStock = productOption.getProductOptionStock();
        return productOptionStock;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "topProducts", key = "10", cacheManager = "topProductCacheManager")
    public List<ProductPopularDto> getTopProducts() {

        List<Long> topProductIds = orderItemRepository.findTopProductIds(PageRequest.of(0, 5));

        return  productMapper.toProductPopularDtoList(topProductIds.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList()));

    }

    @Caching(evict = {
            @CacheEvict(value = "topProducts", allEntries = true)
    })
    public void evictCache_topProducts() {

    }

    @Caching(evict = {
            @CacheEvict(value = "product", allEntries = true)
    })
    public void evictCache_product() {

    }

    @Transactional
    @CacheEvict(value = "product", key = "#product.id", cacheManager = "productCacheManager")
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.id())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDto.name());
        product.setPrice(productDto.price());

        productRepository.save(product);

        return productMapper.toProductDto(product);
    }
}