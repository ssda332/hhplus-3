package hhplus.ecommerce.payment.domain.repository;

import hhplus.ecommerce.payment.domain.entity.Payment;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Payment p WHERE p.approvalNumber = :approvalNumber")
    Optional<Payment> findByApprovalNumberForUpdate(@Param("approvalNumber") Long approvalNumber);
}