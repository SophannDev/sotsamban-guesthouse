package com.sotsamban.guesthouse.controller;

import com.sotsamban.guesthouse.common.MultiSortBuilder;
import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.service.GuestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Object getGuestById(@PathVariable Long id) {
        var guestResponse = guestService.getGuestById(id);
        return ok(guestResponse);
    }

    @GetMapping
    public Object getAllGuests(
            @RequestParam(value = "start_date", required = false) String startDate,
            @RequestParam(value = "end_date", required = false) String endDate,
            @RequestParam(value = "page_number", defaultValue = "0") int pageNumber,
            @RequestParam(value = "page_size", defaultValue = "10") int pageSize,
            @RequestParam(value = "search_value", required = false) String searchValue) {

        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(
                "created_at:asc"
        ).build();
        Pageable pages = PageRequest.of(pageNumber, pageSize,Sort.by(sortBuilder));

        var guestsResponse = guestService.getAllGuests(startDate, endDate, searchValue, pages);
        return ok(guestsResponse);
    }


}
