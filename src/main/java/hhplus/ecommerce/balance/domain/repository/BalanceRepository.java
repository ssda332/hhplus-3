package hhplus.ecommerce.balance.domain.repository;

import hhplus.ecommerce.balance.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByMemberId(Long memberId);
}
