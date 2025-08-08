package com.sotsamban.guesthouse.mapper;

import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.dto.request.room.RoomRequest;
import com.sotsamban.guesthouse.dto.response.room.RoomResponse;
import com.sotsamban.guesthouse.enums.RoomStatus;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public Room toEntity(RoomRequest request) {
        if (request == null) {
            return null;
        }

        Room room = new Room();
        room.setRoomNumber(request.getRoomNumber());
        room.setStatus(request.getStatus() != null ? request.getStatus() : RoomStatus.AVAILABLE);
        room.setPricePerNight(request.getPricePerNight());
        return room;
    }


}
