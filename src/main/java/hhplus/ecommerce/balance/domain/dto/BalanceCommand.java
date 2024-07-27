package hhplus.ecommerce.balance.domain.dto;

import hhplus.ecommerce.balance.domain.entity.Balance;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BalanceCommand {
    public record Charge(Long amount, Balance balance) {
    }
}
