package com.sotsamban.guesthouse.domain.payment;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.enums.PaymentMethodStatus;
import com.sotsamban.guesthouse.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "tb_payment")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long paymentId;

    @Column(name = "amt_paid", nullable = false)
    private BigDecimal amountPaid;

    @Convert(converter = PaymentMethodStatus.Converter.class)
    @Column(name = "pay_mthd", nullable = false)
    private PaymentMethodStatus paymentMethod;

    @Convert(converter = PaymentStatus.Converter.class)
    @Column(name = "pay_sts")
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @Column(name = "pay_dt")
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @OneToOne
    @JoinColumn(name = "bkg_id")
    private Booking booking;

    @Builder
    public Payment(Long paymentId, BigDecimal amountPaid, PaymentMethodStatus paymentMethod,
                   PaymentStatus paymentStatus, LocalDateTime paymentDate, String notes, Booking booking) {
        this.paymentId = paymentId;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.notes = notes;
        this.booking = booking;
    }

}
