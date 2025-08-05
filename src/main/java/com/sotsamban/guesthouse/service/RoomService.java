package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.dto.request.room.RoomRequest;
import org.springframework.data.domain.Pageable;

public interface RoomService {

    void createRoom(RoomRequest roomRequest);

    Object getAllRooms(String searchValue, Pageable pages);

}
