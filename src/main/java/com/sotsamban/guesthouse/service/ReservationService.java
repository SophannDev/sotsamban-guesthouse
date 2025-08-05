package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.dto.request.reservation.ReservationRequest;
import com.sotsamban.guesthouse.dto.response.reservation.ReservationResponse;

public interface ReservationService {

    void createReservation(ReservationRequest request);

}
