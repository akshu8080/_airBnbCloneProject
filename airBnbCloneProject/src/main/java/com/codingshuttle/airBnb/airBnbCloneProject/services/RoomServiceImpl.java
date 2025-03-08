package com.codingshuttle.airBnb.airBnbCloneProject.services;

 import com.codingshuttle.airBnb.airBnbCloneProject.DTO.RoomDto;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
 import com.codingshuttle.airBnb.airBnbCloneProject.Entity.User;
 import com.codingshuttle.airBnb.airBnbCloneProject.exceptions.ResourceNotFoundExceptions;
 import com.codingshuttle.airBnb.airBnbCloneProject.exceptions.UnAuthorizedException;
 import com.codingshuttle.airBnb.airBnbCloneProject.repository.HotelRepository;
import com.codingshuttle.airBnb.airBnbCloneProject.repository.RoomRepository;
 import jakarta.persistence.Id;
 import jakarta.transaction.Transactional;
 import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Hotel not found with ID: "+hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotelwith id: "+ hotelId);
        }

        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);

        if (hotel.isActive()) {
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all rooms in hotel with ID: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Hotel not found with ID: "+hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotelwith id: "+ hotelId);
        }

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room with ID: {}", roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Room not found with ID: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }


    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with Id: {}",roomId);
        Room room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Room not found with ID: "+roomId));


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals( room.getHotel().getOwner())){
            throw new UnAuthorizedException("This user does not own this room with id: "+roomId);
        }

        inventoryService.deleteAllInventories(room);

        roomRepository.deleteById(roomId);


    }
}
