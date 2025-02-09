package com.codingshuttle.airBnb.airBnbCloneProject.services;

 import com.codingshuttle.airBnb.airBnbCloneProject.DTO.RoomDto;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import com.codingshuttle.airBnb.airBnbCloneProject.exceptions.ResourceNotFoundExceptions;
import com.codingshuttle.airBnb.airBnbCloneProject.repository.HotelRepository;
import com.codingshuttle.airBnb.airBnbCloneProject.repository.RoomRepository;
import jakarta.persistence.Id;
 import jakarta.transaction.Transactional;
 import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomrepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId,RoomDto roomDto) {
        log.info("Creating a new room in Hotel with Id: {}",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundExceptions("Hotel Not found with Id: "+hotelId));

        Room room = modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room = roomrepository.save(room);

       if(hotel.isActive()){
            inventoryService.initializeRoomForAYear(room);
       }
        return modelMapper.map(room,RoomDto.class);

     }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Hotel not found with ID: "+hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room with ID: {}", roomId);
        Room room = roomrepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Room not found with ID: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }


    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with Id: {}",roomId);
        Room room = roomrepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Room not found with ID: "+roomId));


        inventoryService.deleteFutureInventories(room);

        roomrepository.deleteById(roomId);




    }
}
