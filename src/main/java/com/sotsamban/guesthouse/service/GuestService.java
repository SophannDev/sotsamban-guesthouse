package com.sotsamban.guesthouse.service;


import com.sotsamban.guesthouse.common.Pagination;
import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.dto.response.guest.GuestResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GuestService {

    void createGuest(GuestRequest guestRequest);

    GuestResponse getGuestById(Long id);

    Object getAllGuests(String startDate, String endDate, String searchValue, Pageable pages);

}
