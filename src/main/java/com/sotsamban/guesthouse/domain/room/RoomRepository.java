package com.sotsamban.guesthouse.domain.room;

import com.sotsamban.guesthouse.dto.response.room.IRoomResponse;
import com.sotsamban.guesthouse.dto.response.room.RoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByRoomNumber(String roomNumber);

    @Query(value = """
        SELECT r.room_id       AS room_id,
               r.room_num      AS room_number,
               r.sts           AS sts,
               r.room_type_id  AS room_type_id,
               rt.type_nm      AS room_type_name,
               r.prce_per_ngt  AS price_per_night,
               rt.base_price   AS base_price,
               r.image_url     AS image_url,
               tg.fname        AS guest_first_name,
               tg.lname        AS guest_last_name,
               tbk.bkg_id      AS booking_id,
               tbk.act_chk_in  AS check_in_date,
               tbk.act_chk_out AS check_out_date
        FROM tb_room r
                 LEFT JOIN tb_room_type rt ON r.room_type_id = rt.rt_id
                 LEFT JOIN tb_guest tg ON r.room_id = tg.guest_id
                 LEFT JOIN tb_booking tbk ON r.room_id = tbk.room_id
    """, nativeQuery = true)
    Page<IRoomResponse> findAllRooms(Pageable pageable);
}
