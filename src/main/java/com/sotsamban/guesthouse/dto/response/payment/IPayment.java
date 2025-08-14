package com.sotsamban.guesthouse.dto.response.payment;

import org.springframework.beans.factory.annotation.Value;

public interface IPayment {
    @Value("#{target.room_id}")
    Long getRoomId();

    @Value("#{target.guest_id}")
    Long getGuestId();

    @Value("#{target.booking_id}")
    Long getBookingId();

    @Value("#{target.payment_id}")
    Long getPaymentId();

    @Value("#{target.amount_paid}")
    String getAmountPaid();

    @Value("#{target.payment_method}")
    String getPaymentMethod();

    @Value("#{target.payment_status}")
    String getPaymentStatus();

    @Value("#{target.payment_date}")
    String getPaymentDate();

    @Value("#{target.notes}")
    String getNotes();

    @Value("#{target.booking_status}")
    String getBookingStatus();

    @Value("#{target.guest_first_name}")
    String getGuestFirstName();

    @Value("#{target.guest_last_name}")
    String getGuestLastName();

    @Value("#{target.room_number}")
    String getRoomNumber();

    @Value("#{target.check_in_date}")
    String getCheckInDate();

    @Value("#{target.check_out_date}")
    String getCheckOutDate();

}
