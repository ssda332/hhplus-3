package hhplus.ecommerce.ordersheet.presentation;

import hhplus.ecommerce.ordersheet.application.OrderSheetFacade;
import hhplus.ecommerce.ordersheet.presentation.dto.OrderSheetRequestDto;
import hhplus.ecommerce.ordersheet.presentation.dto.OrderSheetResponseDto;
import hhplus.ecommerce.ordersheet.domain.service.OrderSheetService;
import hhplus.ecommerce.ordersheet.mapper.OrderSheetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordersheet")
@RequiredArgsConstructor
public class OrderSheetController {

    private final OrderSheetFacade orderSheetFacade;
    private final OrderSheetMapper orderSheetMapper;

    @PostMapping
    public OrderSheetResponseDto createOrderSheet(@RequestBody OrderSheetRequestDto requestDto) {
        return orderSheetMapper.toResponseDto(orderSheetFacade.createOrderSheet(orderSheetMapper.toDto(requestDto)));
    }

    @GetMapping("/{id}")
    public OrderSheetResponseDto getOrderSheet(@PathVariable Long id) {
        return orderSheetMapper.toResponseDto(orderSheetFacade.getOrderSheetById(id));
    }
}