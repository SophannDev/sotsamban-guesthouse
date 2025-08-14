package com.sotsamban.guesthouse.domain.booking;

import com.sotsamban.guesthouse.domain.BaseEntity;
import com.sotsamban.guesthouse.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_booking")
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bkg_id")
    private Long bookingId;

    @Column(name = "act_chk_in")
    private String actualCheckIn;

    @Column(name = "act_chk_out")
    private String actualCheckOut;

    @Convert(converter = BookingStatus.Converter.class)
    @Column(name = "bkg_sts")
    private BookingStatus bookingStatus = BookingStatus.ACTIVE;

    @Column(name = "tot_amt")
    private BigDecimal totalAmount;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @Column(name = "image")
    private String image;

    @Column(name = "guest_id") // Add column mapping
    private Long guestId;

    @Column(name = "room_id") // Add column mapping
    private Long roomId;

    @Builder
    public Booking(Long bookingId, String actualCheckIn, String actualCheckOut, BookingStatus bookingStatus,
                   BigDecimal totalAmount, String notes, String image, Long guestId, Long roomId) {
        this.bookingId = bookingId;
        this.actualCheckIn = actualCheckIn;
        this.actualCheckOut = actualCheckOut;
        this.bookingStatus = bookingStatus;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.image = image;
        this.guestId = guestId;
        this.roomId = roomId;
    }

}
