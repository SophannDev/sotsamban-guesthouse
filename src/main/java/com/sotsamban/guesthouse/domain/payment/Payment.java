package com.sotsamban.guesthouse.domain.payment;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.reservation.Reservation;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Integer paymentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rsv_id", nullable = false)
    private Reservation reservation;

    @DecimalMin(value = "0.01")
    @Column(name = "amt", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_mthd", nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_sts")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "pay_dt")
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Size(max = 100)
    @Column(name = "txn_id")
    private String transactionId;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER, DIGITAL_WALLET, CHECK
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED
    }
}
