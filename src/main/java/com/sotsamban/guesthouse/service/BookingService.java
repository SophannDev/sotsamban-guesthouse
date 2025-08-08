package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;
import org.springframework.data.domain.Pageable;

public interface BookingService {
    void createBooking(BookingRequest request);

    Object getBookings(String startDate, String endDate, String searchValue, Pageable pageable);
}
