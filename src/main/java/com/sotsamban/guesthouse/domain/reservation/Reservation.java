package com.sotsamban.guesthouse.domain.reservation;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.groupcompany.GuestCompanion;
import com.sotsamban.guesthouse.domain.guest.Guest;
import com.sotsamban.guesthouse.domain.payment.Payment;
import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.domain.staff.Staff;
import com.sotsamban.guesthouse.enums.ReservationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rsv_id")
    private Long reservationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @NotNull
    @Column(name = "chk_in", nullable = false)
    private LocalDateTime checkInDate;

    @NotNull
    @Column(name = "chk_out", nullable = false)
    private LocalDateTime checkOutDate;

    @Min(1)
    @Column(name = "num_guest", nullable = false)
    private Integer numberOfGuests = 1;

    @Column(name = "amt")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Convert(converter = ReservationStatus.Converter.class)
    @Column(name = "sts")
    private ReservationStatus status = ReservationStatus.PENDING;

    @Column(name = "spec_req", columnDefinition = "TEXT")
    private String specialRequests;

    @Column(name = "rsv_dt")
    private LocalDateTime reservationDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Booking booking;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuestCompanion> companions;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

}
