package hhplus.ecommerce.balance.domain.entity;

import hhplus.ecommerce.balance.exception.InsufficientBalanceException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "balance")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long amount;

    public void chargeAmount(Long chargeAmount) {
        this.amount = this.amount + chargeAmount;
    }

    public void decreaseAmount(Long amountToSubtract) throws InsufficientBalanceException {
        if (amountToSubtract <= 0) {
            throw new IllegalArgumentException("차감할 금액은 0보다 커야 합니다.");
        }

        if (amount < amountToSubtract) {
            throw new InsufficientBalanceException("잔액이 부족합니다.");
        }

        this.amount -= amountToSubtract;
    }
}
