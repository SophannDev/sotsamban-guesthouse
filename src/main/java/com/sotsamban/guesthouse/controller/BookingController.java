package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.common.MultiSortBuilder;
import com.sotsamban.guesthouse.dto.request.booking.BookingRequest;
import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;
import com.sotsamban.guesthouse.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

     @GetMapping("/{type_nm}")
        public Object getBookings(
             @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
             @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
             @RequestParam(value = "search_value", required = false) String searchValue,
             @PathVariable(value = "type_nm") String bookingType

     ) {

         List<Sort.Order> sortBuilder = new MultiSortBuilder().with(
                 "created_at:asc"
         ).build();
         Pageable pages = PageRequest.of(pageNumber, pageSize,Sort.by(sortBuilder));

         var bookingResp = bookingService.getBookings(bookingType, searchValue, pages);

            return ok(bookingResp);
        }
}
