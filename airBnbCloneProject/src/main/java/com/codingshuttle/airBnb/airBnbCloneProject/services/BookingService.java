package com.codingshuttle.airBnb.airBnbCloneProject.services;


import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingRequest;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.GuestDto;

import java.util.List;


public interface BookingService {


    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
