package hhplus.ecommerce.ordersheet.domain.dto;

import hhplus.ecommerce.balance.domain.entity.Balance;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderSheetCommand {
    public record Create() {
    }
}
