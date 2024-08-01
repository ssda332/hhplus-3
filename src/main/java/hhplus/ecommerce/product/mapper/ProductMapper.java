package hhplus.ecommerce.product.mapper;

import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "createDate", source = "product.createDate")
    @Mapping(target = "productOptionDtos", source = "product.productOptions", qualifiedByName = "mapProductOptions")
    ProductDto toProductDto(Product product);

    @Named("mapProductOptions")
    default List<ProductOptionDto> mapProductOptions(List<ProductOption> productOptions) {
        return productOptions.stream()
                .map(this::mapProductOptionDto)
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", source = "productOption.id")
    @Mapping(target = "name", source = "productOption.name")
    @Mapping(target = "createDate", source = "productOption.createDate")
    @Mapping(target = "productOptionStockDto", source = "productOption.productOptionStock", qualifiedByName = "mapProductOptionStockDto")
    ProductOptionDto mapProductOptionDto(ProductOption productOption);

    @Named("mapProductOptionStockDto")
    default ProductOptionStockDto mapProductOptionStockDto(ProductOptionStock productOptionStock) {
        if (productOptionStock == null) {
            return null;
        }
        return new ProductOptionStockDto(
                productOptionStock.getId(),
                productOptionStock.getStock()
        );
    }

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "optionId", source = "productOption.id")
    @Mapping(target = "optionName", source = "productOption.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "stock", source = "productOption.productOptionStock.stock")
    ProductDetailResponseDto toProductDetailDto(Product product, ProductOption productOption);

    @Named("mapProductDetailOptions")
    default List<ProductDetailResponseDto> mapProductDetailOptions(ProductDto productDto) {
        return productDto.productOptionDtos().stream()
                .map(option -> toProductDetailDto(productDto, option))
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", source = "productDto.id")
    @Mapping(target = "name", source = "productDto.name")
    @Mapping(target = "optionId", source = "productOptionDto.id")
    @Mapping(target = "optionName", source = "productOptionDto.name")
    @Mapping(target = "price", source = "productDto.price")
    @Mapping(target = "stock", source = "productOptionDto.productOptionStockDto.stock")
    ProductDetailResponseDto toProductDetailDto(ProductDto productDto, ProductOptionDto productOptionDto);

    default ProductResponseDto toProductResponseDto(ProductDto productDto) {
        return new ProductResponseDto(
                productDto.id(),
                productDto.name(),
                productDto.price(),
                productDto.createDate(),
                mapProductDetailOptions(productDto)
        );
    }

    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "optionId", source = "productOption.id")
    @Mapping(target = "optionName", source = "productOption.name")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "stock", source = "productOption.productOptionStock.stock")
    ProductPopularDto toProductPopularDto(Product product, ProductOption productOption);

    @Named("mapProductPopularOptions")
    default List<ProductPopularDto> mapProductPopularOptions(Product product) {
        return product.getProductOptions().stream()
                .map(option -> toProductPopularDto(product, option))
                .collect(Collectors.toList());
    }

    default List<ProductPopularDto> toProductPopularDtoList(List<Product> products) {
        return products.stream()
                .flatMap(product -> mapProductPopularOptions(product).stream())
                .collect(Collectors.toList());
    }

    @Mapping(target = "id", source = "productPopularDto.id")
    @Mapping(target = "name", source = "productPopularDto.name")
    @Mapping(target = "optionId", source = "productPopularDto.optionId")
    @Mapping(target = "optionName", source = "productPopularDto.optionName")
    @Mapping(target = "price", source = "productPopularDto.price")
    @Mapping(target = "stock", source = "productPopularDto.stock")
    ProductDetailResponseDto toProductDetailResponseDto(ProductPopularDto productPopularDto);

    default List<ProductDetailResponseDto> toProductDetailResponseDtoList(List<ProductPopularDto> productPopularDtos) {
        return productPopularDtos.stream()
                .map(this::toProductDetailResponseDto)
                .collect(Collectors.toList());
    }
}