package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Inventory;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public  interface InventoryRepository extends JpaRepository<Inventory,Long> {
     void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
