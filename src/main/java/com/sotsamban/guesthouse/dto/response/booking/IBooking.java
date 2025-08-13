package com.sotsamban.guesthouse.dto.response.booking;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface IBooking {

    @Value("#{target.booking_id}")
    Long getBookingId();

    @Value("#{target.room_id}")
    Long getRoomId();

    @Value("#{target.guest_id}")
    Long getGuestId();

    @Value("#{target.check_in}")
    String getCheckIn();

    @Value("#{target.check_out}")
    String getCheckOut();

    @Value("#{target.sts}")
    String getSts();

    @Value("#{target.notes}")
    String getNoted();

    @Value("#{target.price_per_night}")
    BigDecimal getPricePerNight();

    @Value("#{target.room_type_sts}")
    String getRoomTypeSts();

    @Value("#{target.room_number}")
    String getRoomNumber();

    @Value("#{target.first_name}")
    String getFirstName();

    @Value("#{target.last_name}")
    String getLastName();
}
