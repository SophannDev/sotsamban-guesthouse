package com.sotsamban.guesthouse.domain.guestservice;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.servcie.Service;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_guest_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestService extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gsvc_id")
    private Integer guestServiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bkg_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "svc_id", nullable = false)
    private Service service;

    @Min(1)
    @Column(name = "qty", nullable = false)
    private Integer quantity = 1;

    @Column(name = "unit_prc", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "tot_prc", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "svc_dt")
    private LocalDateTime serviceDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "sts")
    private ServiceStatus status = ServiceStatus.PENDING;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    public enum ServiceStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
