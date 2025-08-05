package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.dto.request.reservation.ReservationRequest;
import com.sotsamban.guesthouse.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ReservationController extends BaseRestController{

    private final ReservationService reservationService;

     @PostMapping
     public Object createReservation(@RequestBody @Valid ReservationRequest reservationRequest) {
         reservationService.createReservation(reservationRequest);
         return ok();
     }

//     @GetMapping
//     public Object getAllReservations(
//             @RequestParam(required = false) String searchValue,
//             @RequestParam(defaultValue = "0") int pageNumber,
//             @RequestParam(defaultValue = "10") int pageSize) {
//         List<Sort.Order> sortBuilder = new MultiSortBuilder().with(
//                 "created_at:asc"
//         ).build();
//         Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));
//
//         var reservationsResponse = reservationService.getAllReservations(searchValue, pages);
//         return ok(reservationsResponse);
//     }
}
