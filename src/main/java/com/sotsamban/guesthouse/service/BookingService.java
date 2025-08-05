package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;

public interface BookingService {
    void createBooking(BookingRequest request);
}
