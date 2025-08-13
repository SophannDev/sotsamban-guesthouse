package com.sotsamban.guesthouse.domain.booking;

import com.sotsamban.guesthouse.dto.response.booking.BookingResponse;
import com.sotsamban.guesthouse.dto.response.booking.IBooking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = """
        select tbbk.bkg_id      AS booking_id,
           tbbk.room_id     AS room_id,
           tbbk.guest_id    AS guest_id,
           tbbk.act_chk_in  AS check_in,
           tbbk.act_chk_out AS check_out,
           tbbk.bkg_sts     AS sts,
           tbbk.notes       AS notes,
           tr.prce_per_ngt  AS price_per_night,
           tr.sts           AS room_type_sts,
           tr.room_num      AS room_number,
           tbg.fname        AS first_name,
           tbg.lname        AS last_name
        from tb_booking tbbk
             join tb_room tr on tbbk.room_id = tr.room_id
             join tb_guest tbg on tbbk.guest_id = tbg.guest_id
    """, nativeQuery = true)
    Page<IBooking> findAllBookings(String startDate, String endDate, String searchValue, Pageable pageable);
}
