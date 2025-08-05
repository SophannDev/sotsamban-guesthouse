package com.sotsamban.guesthouse.dto.request.reservation;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.enums.ReservationStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationRequest {

    private Integer guestId;

    private Integer roomId;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private Integer numberOfGuests;

    private BigDecimal totalAmount;

    private ReservationStatus status = ReservationStatus.PENDING;

    private String specialRequests;

    private Long staffId;
}
