package com.codingshuttle.airBnb.airBnbCloneProject.controller;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelInfoDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelPriceDto;
import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelSearchRequest;
import com.codingshuttle.airBnb.airBnbCloneProject.services.HotelService;
import com.codingshuttle.airBnb.airBnbCloneProject.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowsController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;


    @GetMapping("/search")
    public ResponseEntity<Page< HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){

         Page<HotelPriceDto>page =  inventoryService.searchHotels(hotelSearchRequest);
         return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
