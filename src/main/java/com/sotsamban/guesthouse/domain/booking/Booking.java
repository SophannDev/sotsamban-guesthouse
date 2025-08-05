package com.sotsamban.guesthouse.domain.booking;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.domain.feedback.Feedback;
import com.sotsamban.guesthouse.domain.guestservice.GuestService;
import com.sotsamban.guesthouse.domain.reservation.Reservation;
import com.sotsamban.guesthouse.enums.BookingStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_booking")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bkg_id")
    private Integer bookingId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rsv_id", nullable = false, unique = true)
    private Reservation reservation;

    @Column(name = "act_chk_in")
    private LocalDateTime actualCheckIn;

    @Column(name = "act_chk_out")
    private LocalDateTime actualCheckOut;

    @Convert(converter = BookingStatus.Converter.class)
    @Column(name = "bkg_sts")
    private BookingStatus bookingStatus = BookingStatus.ACTIVE;

    @Column(name = "tot_amt", nullable = false)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GuestService> guestServices;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;
}
