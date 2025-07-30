package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.domain.guest.Guest;
import com.sotsamban.guesthouse.domain.guest.GuestRepository;
import com.sotsamban.guesthouse.dto.request.guest.GuestRequest;
import com.sotsamban.guesthouse.dto.response.guest.GuestResponse;
import com.sotsamban.guesthouse.mapper.GuestMapper;
import com.sotsamban.guesthouse.service.GuestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final GuestMapper guestMapper;

    @Override
    public void createGuest(GuestRequest guestRequest) {
        var guest = guestMapper.toEntity(guestRequest);

        guestRepository.save(guest);

    }

    @Override
    public GuestResponse getGuestById(Long id) {
        return null;
    }
}
