package com.sotsamban.guesthouse.dto.response.room;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.domain.room.Room;
import com.sotsamban.guesthouse.enums.RoomStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomResponse {

    private String roomNumber;
    private String roomTypeName;
    private String status;
    private BigDecimal pricePerNight;
    private BigDecimal basePrice;
    private String image;


    @Builder
    public RoomResponse(String roomNumber, String roomTypeName, String status, BigDecimal pricePerNight, BigDecimal basePrice, String image) {
        this.roomNumber = roomNumber;
        this.roomTypeName = roomTypeName;
        this.status = status;
        this.pricePerNight = pricePerNight;
        this.basePrice = basePrice;
        this.image = image;
    }

}
