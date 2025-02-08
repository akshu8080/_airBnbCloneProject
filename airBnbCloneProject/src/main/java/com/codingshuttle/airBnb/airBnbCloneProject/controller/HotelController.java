package com.codingshuttle.airBnb.airBnbCloneProject.controller;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelDto;
import com.codingshuttle.airBnb.airBnbCloneProject.services.HotelService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> createNewHotel(@RequestBody HotelDto hotelDto){
        log.info("Attempting to create new Hotel with name: {}", hotelDto.getName());
        HotelDto hotel =  hotelService.createNewHotel(hotelDto);

        return new ResponseEntity<>(hotel,HttpStatus.CREATED);

    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable(name = "hotelId") Long id){
        HotelDto hotel = hotelService.getHotelById(id);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> updateHotelById(@PathVariable(name = "hotelId") Long id ,
                                                    @RequestBody HotelDto hotelDto){
        HotelDto hotel = hotelService.updateHotelById(id,hotelDto);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotelById(@PathVariable(name = "hotelId")Long id){
        hotelService.deleteHotelById(id);
        return ResponseEntity.noContent().build();

    }

    @PatchMapping("/{hotelId}")
    public ResponseEntity<Void> activateHotel(@PathVariable(name = "hotelId")Long id){
        hotelService.activateHotel(id);
        return ResponseEntity.noContent().build();

    }
 }
