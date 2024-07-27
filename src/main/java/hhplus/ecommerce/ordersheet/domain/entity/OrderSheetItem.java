package hhplus.ecommerce.ordersheet.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderSheetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_sheet_id", nullable = false)
    private OrderSheet orderSheet;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private Long productPrice;

    @Column(nullable = false)
    private Long productOptionId;

    @Column(nullable = false)
    private String productOptionName;

    @Column(nullable = false)
    private Long productCount;

    @Column(nullable = false)
    private LocalDateTime createDate;
}