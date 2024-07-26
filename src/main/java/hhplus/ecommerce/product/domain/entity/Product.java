package hhplus.ecommerce.product.domain.entity;

import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.product.exception.ProductOptionNotFoundException;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductOption> productOptions;

    /*public void decreaseStock(OrderSheetItem item) {
        for (ProductOption productOption : this.productOptions) {
            if (productOption.getId().equals(item.getProductOptionId())) {
                productOption.decreaseStock(item);
                return;
            }
        }
        throw new ProductOptionNotFoundException("상품 옵션 없음");
    }*/
}