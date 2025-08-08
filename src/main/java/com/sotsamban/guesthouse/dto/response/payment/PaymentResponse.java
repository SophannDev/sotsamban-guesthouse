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

    private Long id;
    private Long bookingId;
    private String amountPaid;
    private String paymentMethod;
    private String notes;
    private String createdAt;
    private String updatedAt;

    @Builder
    public PaymentResponse(Long id, Long bookingId, String amountPaid, String paymentMethod, String notes, String createdAt, String updatedAt) {
        this.id = id;
        this.bookingId = bookingId;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.notes = notes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
