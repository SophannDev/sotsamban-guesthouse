package com.sotsamban.guesthouse.domain.payment;

import com.sotsamban.guesthouse.dto.response.payment.IPayment;
import com.sotsamban.guesthouse.dto.response.payment.PaymentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query(value = """
        SELECT p.pay_id as payment_id,
                p.amt_paid as amount_paid,
                p.pay_mthd as payment_method,
                p.pay_sts as payment_status,
                p.pay_dt as payment_date,
                p.notes as notes,
                b.bkg_id as booking_id,
                b.guest_id as guest_id,
                b.room_id as room_id,
                b.bkg_sts as booking_status,
                b.act_chk_in as check_in_date,
                b.act_chk_out as check_out_date,
                g.fname as guest_first_name,
                g.lname as guest_last_name,
                r.room_num as room_number
         FROM tb_payment p
                  LEFT JOIN tb_booking b ON p.bkg_id = b.bkg_id
                  LEFT JOIN tb_guest g ON b.guest_id = g.guest_id
                  LEFT JOIN tb_room r ON b.room_id = r.room_id
         WHERE (?1 IS NULL OR p.pay_sts = ?1)
           AND (?2 IS NULL OR p.pay_mthd = ?2)
           AND (?3 IS NULL OR (
             LOWER(g.fname) LIKE LOWER(CONCAT('%', ?3, '%')) OR
             LOWER(g.lname) LIKE LOWER(CONCAT('%', ?3, '%')) OR
             LOWER(CONCAT(g.fname, ' ', g.lname)) LIKE LOWER(CONCAT('%', ?3, '%')) OR
             LOWER(r.room_num) LIKE LOWER(CONCAT('%', ?3, '%')) OR
             LOWER(p.notes) LIKE LOWER(CONCAT('%', ?3, '%')) OR
             CAST(p.amt_paid AS TEXT) LIKE CONCAT('%', ?3, '%') OR
             CAST(p.pay_id AS TEXT) LIKE CONCAT('%', ?3, '%')
             ))
         ORDER BY p.pay_dt DESC
    """, nativeQuery = true)
    Page<IPayment> findAllPayments(String payMethod, String payStatus, String searchValue, Pageable pageable);
}
