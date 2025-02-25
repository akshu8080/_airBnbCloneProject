package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}