package hhplus.ecommerce.ordersheet.domain.repository;

import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderSheetRepository extends JpaRepository<OrderSheet, Long> {
}