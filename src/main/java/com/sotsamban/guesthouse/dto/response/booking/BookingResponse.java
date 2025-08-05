package com.sotsamban.guesthouse.dto.response.booking;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingResponse {

    private Integer bookingId;
//    private ReservationSummaryResponse reservation;
    private LocalDateTime actualCheckIn;
    private LocalDateTime actualCheckOut;
    private BookingStatus bookingStatus;
    private BigDecimal totalAmount;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Counts for related entities
    private Integer guestServicesCount;
    private Integer feedbacksCount;
}
