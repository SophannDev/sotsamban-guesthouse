package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.booking.BookingRepository;
import com.sotsamban.guesthouse.domain.reservation.Reservation;
import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;
import com.sotsamban.guesthouse.enums.BookingStatus;
import com.sotsamban.guesthouse.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        // 1. Validate and fetch reservation
//        Reservation reservation = reservationRepository.findById(request.getReservationId())
//                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with ID: " + request.getReservationId()));
//
//        // 2. Check if reservation is already booked
//        if (bookingRepository.existsByReservationIdNative(request.getReservationId())) {
//            throw new IllegalArgumentException("Reservation is already booked");
//        }

        // 3. Create booking entity with all fields
        Booking booking = new Booking();

        // Set reservation (required relationship)
//        booking.setReservation(reservation);

        // Set check-in/check-out times
        booking.setActualCheckIn(request.getActualCheckIn());
        booking.setActualCheckOut(request.getActualCheckOut());

        // Set booking status (with default)
        booking.setBookingStatus(request.getBookingStatus() != null ?
                request.getBookingStatus() : BookingStatus.ACTIVE);

        // Set total amount (required)
        booking.setTotalAmount(request.getTotalAmount());

        // Set notes (optional)
        booking.setNotes(request.getNotes());

        // createdAt and updatedAt will be set automatically by @CreationTimestamp and @UpdateTimestamp
        // guestServices and feedbacks are collections that will be empty initially

        bookingRepository.save(booking);

    }
}
