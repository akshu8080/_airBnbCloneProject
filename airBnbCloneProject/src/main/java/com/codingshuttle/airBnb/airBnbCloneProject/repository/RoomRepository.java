package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface RoomRepository extends JpaRepository<Room,Long> {

}
