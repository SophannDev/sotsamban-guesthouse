package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;
import com.sotsamban.guesthouse.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
@Slf4j
public class BookingController extends BaseRestController {

    private final BookingService bookingService;

     @PostMapping
     public Object createBooking(@Valid @RequestBody BookingRequest request) {
         bookingService.createBooking(request);
         return ok();
     }
}
