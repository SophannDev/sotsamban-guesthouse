package com.sotsamban.guesthouse.domain.guestservice;

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
public class GuestService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_service_id")
    private Integer guestServiceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private Service service;

    @Min(1)
    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @DecimalMin(value = "0.0")
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.0")
    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Column(name = "service_date")
    private LocalDateTime serviceDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ServiceStatus status = ServiceStatus.PENDING;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum ServiceStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELLED
    }
}
