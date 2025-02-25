package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
