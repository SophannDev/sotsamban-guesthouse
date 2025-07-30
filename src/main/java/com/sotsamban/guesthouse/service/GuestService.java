package com.sotsamban.guesthouse.service;


import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.dto.response.guest.GuestResponse;

public interface GuestService {

    void createGuest(GuestRequest guestRequest);

    GuestResponse getGuestById(Long id);

}
