package com.sotsamban.guesthouse.dto.request.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingRequest {

    private String actualCheckIn;

    private String actualCheckOut;

    private BookingStatus bookingStatus;

    private BigDecimal totalAmount;

    private String notes;

    private Long guestIds;

    private Long roomIds;
}
