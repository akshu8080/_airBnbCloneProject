package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

 import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByPaymentSessionId(String sessionId);
}
