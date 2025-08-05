package com.sotsamban.guesthouse.domain.staff;

import com.sotsamban.guesthouse.dto.response.staff.IStaffResponse;
import com.sotsamban.guesthouse.dto.response.staff.StaffResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    @Query(value = """
        SELECT s.staff_id,
               s.first_name,
               s.last_name,
               s.email,
               s.phone,
               s.position,
               s.salary,
               s.created_at,
               s.updated_at
        FROM tb_staff s
        WHERE s.staff_id = ?1
""", nativeQuery = true)
    Optional<IStaffResponse> findStaffById(Long id);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM tb_staff tbs WHERE tbs.email = ?1)", nativeQuery = true)
    boolean emailExists(String email);

}
