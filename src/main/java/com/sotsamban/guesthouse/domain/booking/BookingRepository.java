package com.sotsamban.guesthouse.domain.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Additional query methods can be defined here if needed
}
