package hhplus.ecommerce.product.domain.repository;

import hhplus.ecommerce.product.domain.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = {"productOptions", "productOptions.productOptionStock"})
    Optional<Product> findById(Long id);



}
