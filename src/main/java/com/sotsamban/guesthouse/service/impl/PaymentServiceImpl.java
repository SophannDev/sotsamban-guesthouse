package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.booking.BookingRepository;
import com.sotsamban.guesthouse.domain.payment.Payment;
import com.sotsamban.guesthouse.domain.payment.PaymentRepository;
import com.sotsamban.guesthouse.dto.request.payment.PaymentRequest;
import com.sotsamban.guesthouse.dto.response.payment.PaymentResponse;
import com.sotsamban.guesthouse.enums.PaymentMethodStatus;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.service.PaymentService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final BookingRepository bookingRepository;

    // Implement the createPayment method
    @Override
    public void createPayment(PaymentRequest request) {

        var bookingId = bookingRepository.findById(request.getBookingId()).orElseThrow(() -> new BusinessException(StatusCode.NOT_FOUND));

         Payment payment = new Payment();
         payment.setAmountPaid(new BigDecimal(request.getAmountPaid()));
         payment.setPaymentMethod(PaymentMethodStatus.valueOf(request.getPaymentMethod()));
         payment.setNotes(request.getNotes());
         payment.setBooking(bookingId);

         paymentRepository.save(payment);
    }

    @Override
    public Object getAllPayments(String searchValue, Pageable pageable) {

        var findAllPayments = paymentRepository.findAllPayments(searchValue, pageable);


        return null;
    }
}
