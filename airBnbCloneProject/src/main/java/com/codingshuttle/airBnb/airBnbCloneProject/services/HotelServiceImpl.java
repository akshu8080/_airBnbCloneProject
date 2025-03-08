package com.codingshuttle.airBnb.airBnbCloneProject.services;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelInfoDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.RoomDto;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.User;
import com.codingshuttle.airBnb.airBnbCloneProject.exceptions.ResourceNotFoundExceptions;
import com.codingshuttle.airBnb.airBnbCloneProject.exceptions.UnAuthorizedException;
import com.codingshuttle.airBnb.airBnbCloneProject.repository.HotelRepository;
import com.codingshuttle.airBnb.airBnbCloneProject.repository.RoomRepository;
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
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;


     @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating a new hotel with name: {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);

        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}", hotelDto.getId());

         return  modelMapper.map(hotel , HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
         log.info("Getting the Hotel with ID: {}",id);
         Hotel hotel = hotelRepository
                 .findById(id)
                 .orElseThrow(()  ->  new ResourceNotFoundExceptions("Hotel not found with ID:"+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())){
             throw new UnAuthorizedException("This user does not own this hotelwith id: "+id);
        }
        return modelMapper.map(hotel , HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating the Hotel with ID: {}",id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()  ->  new ResourceNotFoundExceptions("Hotel not found with ID:"+id));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotelwith id: "+id);
        }

        modelMapper.map(hotelDto,hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(long id) {
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()  ->  new ResourceNotFoundExceptions("Hotel not found with ID:"+id));


        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotelwith id: "+id);
        }


        for (Room room:hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);

     }

    @Override
    @Transactional
     public void activateHotel(Long hotelId) {
        log.info("Activating  the Hotel with ID: {}",hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()  ->  new ResourceNotFoundExceptions("Hotel not found with ID:"+hotelId));

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotel with id: "+ hotelId);
        }
         hotel.setActive(true);

         for (Room room:hotel.getRooms()){
             inventoryService.initializeRoomForAYear(room);
         }
    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(()  ->  new ResourceNotFoundExceptions("Hotel not found with ID:"+hotelId));

        List<RoomDto> rooms = hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .toList();

        return new HotelInfoDto(modelMapper.map(hotel,HotelDto.class),rooms);
    }


}

