package com.sotsamban.guesthouse.dto.request.booking;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.enums.BookingStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookingRequest {

    @NotNull(message = "Reservation ID is required")
    private Integer reservationId;

    private LocalDateTime actualCheckIn;
    private LocalDateTime actualCheckOut;

    private BookingStatus bookingStatus = BookingStatus.ACTIVE;

    @NotNull(message = "Total amount is required")
    @DecimalMin(value = "0.0", message = "Total amount must be non-negative")
    private BigDecimal totalAmount;

    @Size(max = 1000, message = "Notes must not exceed 1000 characters")
    private String notes;
}
