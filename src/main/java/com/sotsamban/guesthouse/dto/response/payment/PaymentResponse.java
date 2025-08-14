package com.sotsamban.guesthouse.dto.response.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentResponse {

    private Long paymentId;
    private String amountPaid;
    private String paymentMethod;
    private String paymentMethodName;
    private String paymentStatus;
    private String paymentStatusName;
    private String paymentDate;
    private String notes;
    private Long bookingId;
    private Long guestId;
    private Long roomId;
    private String bookingStatus;
    private String guestFirstName;
    private String guestLastName;
    private String roomNumber;
    private String checkInDate;
    private String checkOutDate;

    @Builder
    public PaymentResponse(Long paymentId, String amountPaid, String paymentMethod, String paymentMethodName, String paymentStatus, String paymentStatusName,
                           String paymentDate, String notes, Long bookingId, Long guestId, Long roomId,
                           String bookingStatus, String guestFirstName, String guestLastName,
                           String roomNumber, String checkInDate, String checkOutDate) {
        this.paymentId = paymentId;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.paymentMethodName = paymentMethodName;
        this.paymentStatus = paymentStatus;
        this.paymentStatusName = paymentStatusName;
        this.paymentDate = paymentDate;
        this.notes = notes;
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.roomId = roomId;
        this.bookingStatus = bookingStatus;
        this.guestFirstName = guestFirstName;
        this.guestLastName = guestLastName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }



}
