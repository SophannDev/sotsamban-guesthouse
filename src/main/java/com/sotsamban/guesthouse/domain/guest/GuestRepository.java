package com.sotsamban.guesthouse.domain.guest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    @Query(value = """
        SELECT * FROM tb_guest tbg
        WHERE (?1 IS NULL OR tbg.created_at >= ?1)
        AND (?2 IS NULL OR tbg.created_at <= ?2)
        AND (?3 IS NULL OR 
             tbg.fname ILIKE CONCAT('%', ?3, '%') OR 
             tbg.lname ILIKE CONCAT('%', ?3, '%'))
    """, nativeQuery = true)
    Page<Guest> findAllByStartDateBetweenAndEndDateBetween(String startDate, String endDate, String searchValue, Pageable pageable);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);


}
