package hhplus.ecommerce.product.domain.entity;

import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.product.exception.ProductOptionNotFoundException;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDateTime createDate;

    @OneToOne(mappedBy = "productOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductOptionStock productOptionStock;

    public void decreaseStock(OrderSheetItem item) {
        if (this.productOptionStock == null) {
            throw new ProductOptionNotFoundException("재고 정보 없음");
        }
        this.productOptionStock.decreaseStock(item);
    }
}