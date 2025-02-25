package com.codingshuttle.airBnb.airBnbCloneProject.services;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelSearchRequest;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room  );

    void deleteAllInventories(Room room);


    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
