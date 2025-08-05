package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.domain.room.RoomRepository;
import com.sotsamban.guesthouse.domain.roomtype.RoomType;
import com.sotsamban.guesthouse.domain.roomtype.RoomTypeRepository;
import com.sotsamban.guesthouse.dto.request.room.RoomRequest;
import com.sotsamban.guesthouse.dto.response.room.RoomMainResponse;
import com.sotsamban.guesthouse.dto.response.room.RoomResponse;
import com.sotsamban.guesthouse.dto.response.roomtype.RoomTypeMainResponse;
import com.sotsamban.guesthouse.mapper.RoomMapper;
import com.sotsamban.guesthouse.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomMapper roomMapper;

    @Override
    public void createRoom(RoomRequest roomRequest) {

        if (roomRepository.existsByRoomNumber(roomRequest.getRoomNumber())) {
            throw new IllegalArgumentException("Room number " + roomRequest.getRoomNumber() + " already exists");
        }

        RoomType roomType = roomTypeRepository.findById(roomRequest.getRoomTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Room type not found with ID: " + roomRequest.getRoomTypeId()));


        var room = roomMapper.toEntity(roomRequest);

        room.setRoomType(roomType);

        roomRepository.save(room);

    }

    @Override
    public Object getAllRooms(String searchValue, Pageable pages) {

        var roomList = roomRepository.findAllRooms(pages);

        var roomsResponse = roomList.stream()
                .map(r -> RoomResponse.builder()
                        .roomNumber(r.getRoomNumber())
                        .roomTypeName(r.getRoomTypeName())
                        .status(r.getStatus())
                        .basePrice(r.getBasePrice())
                        .maxOccupancy(r.getMaxOccupancy())
                        .build())
                .toList();

        return RoomMainResponse.builder()
                .rooms(roomsResponse)
                .page(roomList)
                .build();
    }
}
