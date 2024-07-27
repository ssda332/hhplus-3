package hhplus.ecommerce.payment.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column(nullable = false)
    private Long orderSheetId;

    @Column
    private Long orderId;

    @Column
    private Long approvalNumber;

    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private LocalDateTime createDate;

}