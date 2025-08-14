package com.sotsamban.guesthouse.dto.response.payment;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sotsamban.guesthouse.common.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentMainResponse {

    private List<PaymentResponse> payments;
    private Pagination page;

    @Builder
    public PaymentMainResponse(List<PaymentResponse> payments, Page<?> page) {
        this.payments = payments;
        this.page = new Pagination(page);
    }

}
