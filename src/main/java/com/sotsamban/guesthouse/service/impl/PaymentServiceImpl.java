package com.sotsamban.guesthouse.service.impl;

import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.domain.booking.Booking;
import com.sotsamban.guesthouse.domain.booking.BookingRepository;
import com.sotsamban.guesthouse.domain.payment.Payment;
import com.sotsamban.guesthouse.domain.payment.PaymentRepository;
import com.sotsamban.guesthouse.dto.request.payment.PaymentRequest;
import com.sotsamban.guesthouse.dto.response.payment.PaymentMainResponse;
import com.sotsamban.guesthouse.dto.response.payment.PaymentResponse;
import com.sotsamban.guesthouse.enums.PaymentMethodStatus;
import com.sotsamban.guesthouse.enums.PaymentStatus;
import com.sotsamban.guesthouse.exception.BusinessException;
import com.sotsamban.guesthouse.service.PaymentService;
import com.sotsamban.guesthouse.utils.DateTimeUtils;
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
    public Payment createPayment(PaymentRequest request) {


        // Validate booking exists
        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + request.getBookingId()));

        // Convert amount string to BigDecimal
        BigDecimal amountPaid;
        try {
            amountPaid = new BigDecimal(request.getAmountPaid());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid amount format: " + request.getAmountPaid());
        }

        // Convert payment method string to enum
        PaymentMethodStatus paymentMethod = PaymentMethodStatus.fromValue(request.getPaymentMethod());
        if (paymentMethod == null) {
            throw new BusinessException(StatusCode.PAYMENT_METHOD_NOT_FOUND);
        }

        // Build and save payment
        Payment payment = Payment.builder()
                .booking(booking)
                .amountPaid(amountPaid)
                .paymentMethod(paymentMethod)
                .paymentStatus(PaymentStatus.PENDING) // Default status
                .paymentDate(DateTimeUtils.getDateTimeNow())
                .notes(request.getNotes())
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public Object getAllPayments(String payMethod, String payStatus, String searchValue, Pageable pageable) {

        var findAllPayments = paymentRepository.findAllPayments(payMethod, payStatus, searchValue, pageable);

        var payLists = findAllPayments.getContent().stream()
                .map(payment -> {

                    var paymentStatus = "";
                    var paymentMethod = "";

                    if (payment.getPaymentStatus().equals("1")) {
                        paymentStatus = PaymentStatus.PENDING.getLabel();
                    } else if (payment.getPaymentStatus().equals("2")) {
                        paymentStatus = PaymentStatus.PAID.getLabel();
                    } else if (payment.getPaymentStatus().equals("3")) {
                        paymentStatus = PaymentStatus.FAILED.getLabel();
                    } else if (payment.getPaymentStatus().equals("4")) {
                        paymentStatus = PaymentStatus.REFUNDED.getLabel();
                    } else {
                        throw new BusinessException(StatusCode.NOT_FOUND, "Payment status not found");
                    }

                    if (payment.getPaymentMethod().equals("1")) {
                        paymentMethod = PaymentMethodStatus.CASH.getLabel();
                    } else if (payment.getPaymentMethod().equals("2")) {
                        paymentMethod = PaymentMethodStatus.CREDIT_CARD.getLabel();
                    } else if (payment.getPaymentMethod().equals("3")) {
                        paymentMethod = PaymentMethodStatus.BANK_TRANSFER.getLabel();
                    } else {
                        throw new BusinessException(StatusCode.NOT_FOUND, "Payment method not found");
                    }

                        return PaymentResponse.builder()
                            .paymentId(payment.getPaymentId())
                            .amountPaid(payment.getAmountPaid())
                            .paymentMethod(payment.getPaymentMethod())
                            .paymentMethodName(paymentMethod)
                            .paymentStatus(payment.getPaymentStatus())
                            .paymentStatusName(paymentStatus)
                            .paymentDate(payment.getPaymentDate())
                            .notes(payment.getNotes())
                            .bookingId(payment.getBookingId())
                            .guestId(payment.getGuestId())
                            .roomId(payment.getRoomId())
                            .bookingStatus(payment.getBookingStatus())
                            .guestFirstName(payment.getGuestFirstName())
                            .guestLastName(payment.getGuestLastName())
                            .roomNumber(payment.getRoomNumber())
                            .checkInDate(payment.getCheckInDate())
                            .checkOutDate(payment.getCheckOutDate())
                            .build();
                    })
                    .toList();

        return PaymentMainResponse.builder()
                .payments(payLists)
                .page(findAllPayments)
                .build();
    }
}
