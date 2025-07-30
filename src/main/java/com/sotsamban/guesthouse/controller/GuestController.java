package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.domain.guest.Guest;
import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.dto.response.ApiResponse;
import com.sotsamban.guesthouse.dto.response.guest.GuestResponse;
import com.sotsamban.guesthouse.service.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/v1/guests")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @PostMapping
    public Object createGuest(@RequestBody @Valid GuestRequest guestRequest) {
        guestService.createGuest(guestRequest);
        return ok();
    }


}
