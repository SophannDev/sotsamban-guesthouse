package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.domain.reservation.Reservation;
import com.sotsamban.guesthouse.domain.reservation.ReservationRepository;
import com.sotsamban.guesthouse.dto.request.reservation.ReservationRequest;
import com.sotsamban.guesthouse.dto.response.reservation.ReservationResponse;
import com.sotsamban.guesthouse.service.ReservationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public void createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setCheckInDate(request.getCheckInDate());
        reservation.setCheckOutDate(request.getCheckOutDate());
        reservation.setNumberOfGuests(request.getNumberOfGuests());
        reservation.setTotalAmount(request.getTotalAmount());
        reservation.setStatus(request.getStatus());
        reservation.setSpecialRequests(request.getSpecialRequests());
        reservationRepository.save(reservation);
    }
}
