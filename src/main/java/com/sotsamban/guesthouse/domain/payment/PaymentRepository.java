package com.sotsamban.guesthouse.domain.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = """
        select tbp.bkg_id AS booking_id,
               tbp.pay_id AS payment_id,
               tbp.pay_dt AS payment_date,
               tbp.amt_paid AS payment_amount,
               tbp.pay_sts AS payment_status,
               tbp.pay_mthd AS payment_method,
               tbg.fname AS first_name,
                 tbg.lname AS last_name
        from tb_payment tbp
        join tb_guest tbg on tbp.pay_id = tbg.guest_id
    """, nativeQuery = true)
    Page<Object> findAllPayments(String searchValue, Pageable pageable);
}
