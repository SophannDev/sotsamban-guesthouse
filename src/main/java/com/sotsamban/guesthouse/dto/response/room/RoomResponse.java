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
    private String roomType;
    private String roomTypeName;
    private String status;
    private String roomStatusName;
    private BigDecimal pricePerNight;
    private BigDecimal basePrice;
    private String image;
    private String guestFirstName;
    private String guestLastName;
    private String actualCheckIn;
    private String actualCheckOut;



    @Builder
    public RoomResponse(String roomNumber, String roomType, String roomTypeName, String status,String roomStatusName, BigDecimal pricePerNight, BigDecimal basePrice, String image,
                        String guestFirstName, String guestLastName, String actualCheckIn, String actualCheckOut) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomTypeName = roomTypeName;
        this.status = status;
        this.roomStatusName = roomStatusName;
        this.pricePerNight = pricePerNight;
        this.basePrice = basePrice;
        this.image = image;
        this.guestFirstName = guestFirstName;
        this.guestLastName = guestLastName;
        this.actualCheckIn = actualCheckIn;
        this.actualCheckOut = actualCheckOut;
    }

}
