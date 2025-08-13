package com.sotsamban.guesthouse.dto.response.booking;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingResponse {

    private Long bookingId;
    private Long roomId;
    private String roomNumber;
    private Long guestId;
    private String firstName;
    private String lastName;
    private String actualCheckIn;
    private String actualCheckOut;
    private String bookingStatus;
    private BigDecimal totalAmount;
    private String notes;

    @Builder
    public BookingResponse(Long bookingId, Long roomId, String roomNumber, Long guestId, String firstName, String LastName, String actualCheckIn, String actualCheckOut,
                           String bookingStatus, BigDecimal totalAmount, String notes) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = LastName;
        this.actualCheckIn = actualCheckIn;
        this.actualCheckOut = actualCheckOut;
        this.bookingStatus = bookingStatus;
        this.totalAmount = totalAmount;
        this.notes = notes;
    }
}
