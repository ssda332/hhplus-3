package hhplus.ecommerce.ordersheet.mapper;

import hhplus.ecommerce.ordersheet.domain.dto.OrderSheetDto;
import hhplus.ecommerce.ordersheet.presentation.dto.OrderSheetRequestDto;
import hhplus.ecommerce.ordersheet.presentation.dto.OrderSheetItemRequestDto;
import hhplus.ecommerce.ordersheet.presentation.dto.OrderSheetResponseDto;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderSheetMapper {
    //OrderSheet toEntity(OrderSheetRequestDto dto);
    OrderSheetResponseDto toResponseDto(OrderSheet orderSheet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paymentStatus", constant = "PENDING")
    @Mapping(target = "createDate", expression = "java(java.time.LocalDateTime.now())")
    OrderSheet toEntity(OrderSheetDto dto);

    OrderSheetDto toDto(OrderSheetRequestDto dto);
}