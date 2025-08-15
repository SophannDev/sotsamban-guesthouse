package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.booking.BookingRepository;
import com.sotsamban.guesthouse.domain.room.RoomRepository;
import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.dto.response.booking.BookingMainResponse;
import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;
import com.sotsamban.guesthouse.dto.response.booking.IBooking;
import com.sotsamban.guesthouse.enums.BookingStatus;
import com.sotsamban.guesthouse.enums.RoomStatus;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.service.BookingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    @Override
    @Transactional
    public void createBooking(BookingRequest request) {

        // find room by id
        var roomId = roomRepository.findById(request.getRoomIds())
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + request.getRoomIds()));

        if (roomId.getStatus() != RoomStatus.AVAILABLE) {
            throw new BusinessException(StatusCode.ROOM_NOT_AVAILABLE);
        }

        var booking = Booking.builder()
                .actualCheckIn(request.getActualCheckIn())
                .actualCheckOut(request.getActualCheckOut())
                .bookingStatus(request.getBookingStatus() != null ? request.getBookingStatus() : BookingStatus.CONFIRMED)
                .totalAmount(request.getTotalAmount())
                .notes(request.getNotes())
                .roomId(request.getRoomIds())
                .guestId(request.getGuestIds())
                .build();

        bookingRepository.save(booking);

    }

    @Override
    public Object getBookings(String bookingType, String searchValue, Pageable pageable) {

        Page<IBooking> findAllBookings = bookingRepository.findAllBookings(bookingType, searchValue, pageable);

        var bookingResponses = findAllBookings.stream()
                .map(booking -> {

                    var roomId = roomRepository.findById(booking.getRoomId())
                            .orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND, "Room not found with ID: " + booking.getRoomId()));

                    var bookintStatus = "";
                    var roomTypeName = "";

                    if (booking.getSts().equals("0")) {
                        bookintStatus = BookingStatus.CANCELLED.name();
                    } else if (booking.getSts().equals("1")) {
                        bookintStatus = BookingStatus.CONFIRMED.name();
                    } else if (booking.getSts().equals("2")) {
                        bookintStatus = BookingStatus.COMPLETED.name();
                    } else {
                        throw new BusinessException(StatusCode.NOT_FOUND, "Invalid booking status: " + booking.getSts());
                    }

                    if (roomId.getRoomType().getTypeName().equals("1")) {
                        roomTypeName = "Single bed";
                    } else if (roomId.getRoomType().getTypeName().equals("2")) {
                        roomTypeName = "Double bed";
                    } else {
                        roomTypeName = "Default bed";
                    }

                    return BookingResponse.builder()
                            .bookingId(booking.getBookingId())
                            .roomId(booking.getRoomId())
                            .roomNumber(booking.getRoomNumber())
                            .roomTypeName(roomTypeName)
                            .guestId(booking.getGuestId())
                            .actualCheckIn(booking.getCheckIn())
                            .actualCheckOut(booking.getCheckOut())
                            .bookingStatus(booking.getSts())
                            .bookingStatusName(bookintStatus)
                            .totalAmount(booking.getPricePerNight())
                            .notes(booking.getNoted())
                            .firstName(booking.getFirstName())
                            .lastName(booking.getLastName())
                            .build();
                })
                .toList();

        return BookingMainResponse.builder()
                .bookings(bookingResponses)
                .page(findAllBookings)
                .build();
    }
}
