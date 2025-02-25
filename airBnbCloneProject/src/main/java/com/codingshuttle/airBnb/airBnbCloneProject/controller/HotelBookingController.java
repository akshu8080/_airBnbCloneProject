package com.codingshuttle.airBnb.airBnbCloneProject.controller;


import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingRequest;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.GuestDto;
import com.codingshuttle.airBnb.airBnbCloneProject.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {

    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequest));
    }


    @PostMapping("/{bookingId}/addGuests")
    public  ResponseEntity<BookingDto> addGuests(@PathVariable Long bookingId,
                                                 @RequestBody List<GuestDto>guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId,guestDtoList));
    }



}