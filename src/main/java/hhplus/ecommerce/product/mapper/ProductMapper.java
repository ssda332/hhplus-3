package hhplus.ecommerce.product.mapper;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.dto.ProductDetailResponseDto;
import hhplus.ecommerce.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "orderItem.productId")
    @Mapping(target = "name", source = "orderItem.productName")
    @Mapping(target = "price", source = "orderItem.productPrice")
    @Mapping(target = "createDate", source = "orderItem.createDate")
    @Mapping(target = "stock", source = "orderItem.productCount")
    ProductDetailResponseDto orderItemtoProductDetailDto(OrderItem orderItem);

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "optionId", source = "productOption.id")
    @Mapping(target = "optionName", source = "productOption.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "createDate", source = "product.createDate")
    @Mapping(target = "stock", source = "productOption.productOptionStock.stock")
    ProductDetailResponseDto toProductDetailDto(Product product, ProductOption productOption);

    @Named("mapProductOptions")
    default List<ProductDetailResponseDto> mapProductOptions(Product product) {
        return product.getProductOptions().stream()
                .map(option -> toProductDetailDto(product, option))
                .collect(Collectors.toList());
    }

    default ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCreateDate(),
                mapProductOptions(product)
        );
    }
}