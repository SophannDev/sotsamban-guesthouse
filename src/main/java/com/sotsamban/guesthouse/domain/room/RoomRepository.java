package com.sotsamban.guesthouse.domain.room;

import com.sotsamban.guesthouse.dto.response.room.RoomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsByRoomNumber(String roomNumber);

    @Query(value = """
        SELECT 
            tbr.room_number,
            tbr.status,
            tbr.base_price,
            tbr.max_occupancy,
            tbr.is_accessible
        FROM tb_room tbr
        ORDER BY tbr.room_number
    """, nativeQuery = true)
    Page<RoomResponse> findAllRooms(Pageable pageable);
}
