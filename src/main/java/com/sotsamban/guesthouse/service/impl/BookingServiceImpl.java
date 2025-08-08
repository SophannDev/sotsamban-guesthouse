package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.booking.BookingRepository;
import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.enums.BookingStatus;
import com.sotsamban.guesthouse.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    @Transactional
    public void createBooking(BookingRequest request) {

        var booking = Booking.builder()
                .actualCheckIn(request.getActualCheckIn())
                .actualCheckOut(request.getActualCheckOut())
                .bookingStatus(request.getBookingStatus() != null ? request.getBookingStatus() : BookingStatus.ACTIVE)
                .totalAmount(request.getTotalAmount())
                .notes(request.getNotes())
                .roomId(request.getRoomIds())
                .guestId(request.getGuestIds())
                .build();

        bookingRepository.save(booking);

    }

    @Override
    public Object getBookings(String startDate, String endDate, String searchValue, Pageable pageable) {
        return null;
    }
}
