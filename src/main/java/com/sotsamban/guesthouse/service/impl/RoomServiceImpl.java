package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.domain.room.RoomRepository;
import com.sotsamban.guesthouse.domain.roomtype.RoomType;
import com.sotsamban.guesthouse.domain.roomtype.RoomTypeRepository;
import com.sotsamban.guesthouse.dto.request.room.RoomRequest;
import com.sotsamban.guesthouse.dto.response.room.RoomMainResponse;
import com.sotsamban.guesthouse.dto.response.room.RoomResponse;
import com.sotsamban.guesthouse.enums.RoomStatus;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.mapper.RoomMapper;
import com.sotsamban.guesthouse.service.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

        Room room = new Room();
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setStatus(roomRequest.getStatus() != null ? roomRequest.getStatus() : RoomStatus.AVAILABLE);
        room.setPricePerNight(roomRequest.getPricePerNight());
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
                        .pricePerNight(r.getPricePerNight())
                        .basePrice(r.getBasePrice())
                        .status(r.getStatus())
                        .image(r.getImageUrl())
                        .build())
                .toList();

        return RoomMainResponse.builder()
                .rooms(roomsResponse)
                .page(roomList)
                .build();
    }

    @Override
    public Object getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND));

        RoomResponse roomResponse = RoomResponse.builder()
                .roomNumber(room.getRoomNumber())
                .roomTypeName(room.getRoomType().getTypeName())
                .pricePerNight(room.getPricePerNight())
                .basePrice(room.getRoomType().getBasePrice())
                .status(room.getStatus().getLabel())
                .build();
        return roomResponse;

    }
}
