package com.sotsamban.guesthouse.service;

import com.sotsamban.guesthouse.domain.payment.Payment;
import com.sotsamban.guesthouse.dto.request.payment.PaymentRequest;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    Payment createPayment(PaymentRequest request);

    Object getAllPayments(String payMethod, String payStatus, String searchValue, Pageable pageable);

}
