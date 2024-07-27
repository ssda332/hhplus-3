package hhplus.ecommerce.order.domain.repository;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT oi.productId FROM OrderItem oi GROUP BY oi.productId ORDER BY COUNT(oi) DESC")
    List<Long> findTopProductIds(Pageable pageable);
}