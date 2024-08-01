package hhplus.ecommerce.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
//@Setter
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column
    private Long productId;

    @Column
    private String productName;

    @Column
    private Long productOptionId;

    @Column
    private String productOptionName;

    @Column
    private Long productPrice;

    @Column
    private Long productCount;

    @Column
    private LocalDateTime createDate;

    public OrderItem(Long productId, String productName, Long productPrice, Long productCount) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
    }
}