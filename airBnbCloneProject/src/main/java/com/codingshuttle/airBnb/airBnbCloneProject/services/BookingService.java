package com.codingshuttle.airBnb.airBnbCloneProject.services;


import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.BookingRequest;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.GuestDto;
import com.stripe.model.Event;

import java.util.List;
import java.util.Map;


public interface BookingService {


    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);
}
