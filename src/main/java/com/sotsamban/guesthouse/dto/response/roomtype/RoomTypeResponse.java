package com.sotsamban.guesthouse.dto.response.roomtype;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoomTypeResponse {

    private Integer roomTypeId;
    private String typeName;
    private String description;
    private BigDecimal baseRate;
    private Integer standardOccupancy;
    private Integer maxOccupancy;
    private String amenities;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Include room count
    private Integer roomCount;
}
