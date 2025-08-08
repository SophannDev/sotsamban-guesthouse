package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.roomtype.RoomType;
import com.sotsamban.guesthouse.domain.roomtype.RoomTypeRepository;
import com.sotsamban.guesthouse.dto.request.roomtype.RoomTypeRequest;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.service.RoomTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public void createRoomType(RoomTypeRequest payload) {

        var existTypeName = roomTypeRepository.existsRoomTypeByTypeName(payload.getTypeName().getValue());
        if (existTypeName) {
            throw new BusinessException(StatusCode.ROOM_TYPE_ALREADY_EXISTS);
        }

        RoomType roomType = new RoomType();
        roomType.setTypeName(payload.getTypeName().getValue());
        roomType.setDescription(payload.getDescription());
        roomType.setBasePrice(payload.getBasePrice());

        roomTypeRepository.save(roomType);

    }
}
