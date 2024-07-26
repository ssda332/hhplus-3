package hhplus.ecommerce.product.domain.repository;

import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductOptionStockRepository extends JpaRepository<ProductOptionStock, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT pos FROM ProductOptionStock pos WHERE pos.productOption.id = :productOptionId")
    Optional<ProductOptionStock> findByProductOptionIdForUpdate(@Param("productOptionId") Long productOptionId);

    Optional<ProductOptionStock> findByProductOptionId(Long productOptionId);
}
