package com.codingshuttle.airBnb.airBnbCloneProject.services;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingRequest;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.GuestDto;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.*;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.enums.BookingStatus;
import com.codingshuttle.airBnb.airBnbCloneProject.exceptions.ResourceNotFoundExceptions;
import com.codingshuttle.airBnb.airBnbCloneProject.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
 import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final GuestRepository guestRepository;


    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public BookingDto initialiseBooking(BookingRequest bookingRequest) {

        log.info("Initialising booking for hotel : {}, room: {}, date {}-{}", bookingRequest.getHotelId(),
                bookingRequest.getRoomId(), bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());

        Hotel hotel = hotelRepository.findById(bookingRequest.getHotelId()).orElseThrow(() ->
                new ResourceNotFoundExceptions("Hotel not found with id: " + bookingRequest.getHotelId()));

        Room room = roomRepository.findById(bookingRequest.getRoomId()).orElseThrow(() ->
                new ResourceNotFoundExceptions("Room not found with id: " + bookingRequest.getRoomId()));

        List<Inventory> inventoryList = inventoryRepository.findAndAvailableInventory(room.getId(),
                bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate(), bookingRequest.getRoomsCount());

        long daysCount = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate()) + 1;
         log.info("Size of Inventory is :{}",inventoryList.size())   ;
        if (inventoryList.size() != daysCount) {
            throw new IllegalStateException("Room is not available anymore");
        }

        // Reserve the room/ update the booked count of inventories

        for (Inventory inventory : inventoryList) {
            inventory.setReservedCount(inventory.getReservedCount() + bookingRequest.getRoomsCount());
        }

        inventoryRepository.saveAll(inventoryList);

        // Create the Booking


        // TODO: calculate dynamic amount

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .user( getCurrentUser())
                .roomsCount(bookingRequest.getRoomsCount())
                .amount(BigDecimal.TEN)
                .build();

        booking = bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    public BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList) {
        log.info("Adding  Guests for Booking with Id : {}",bookingId);

         Booking  booking =  bookingRepository.findById(bookingId).orElseThrow(() ->
                new ResourceNotFoundExceptions(" Booking  not found with id: " + bookingId));

         if(hasBookingExpired(booking)){
             throw new IllegalStateException("Booking has Already Expired ");
         }

         if(booking.getBookingStatus()!=BookingStatus.RESERVED){
             throw new IllegalStateException("Booking is not under RESERVED state , cannot add Guests");
         }

         for (GuestDto guestDto:guestDtoList){
             Guest guest = modelMapper.map(guestDto,Guest.class);
             guest.setUser(getCurrentUser());
             guest = guestRepository.save(guest);
             booking.getGuests().add(guest);
         }


         booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
         bookingRepository.save(booking);
         return modelMapper.map(booking, BookingDto.class);
    }

    public boolean hasBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }

    public User getCurrentUser(){
        User user = new User();
        user.setId(2L);
        return user;
    }
}

