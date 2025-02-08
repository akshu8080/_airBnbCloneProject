package com.codingshuttle.airBnb.airBnbCloneProject.services;


import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(long id);

    void activateHotel(Long hotelId);
}
