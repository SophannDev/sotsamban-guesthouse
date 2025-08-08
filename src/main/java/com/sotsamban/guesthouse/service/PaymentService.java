package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.dto.request.payment.PaymentRequest;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    void createPayment(PaymentRequest request);

    Object getAllPayments(String searchValue, Pageable pageable);

}
