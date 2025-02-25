package com.codingshuttle.airBnb.airBnbCloneProject.repository;

import com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelPriceDto;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

 public  interface HotelMinPriceRepository extends JpaRepository<HotelMinPrice,Long> {
     @Query("""
            SELECT new com.codingshuttle.airBnb.airBnbCloneProject.DTO.HotelPriceDto(i.hotel, AVG(i.price))
            FROM HotelMinPrice i
            WHERE i.hotel.city = :city
                AND i.date BETWEEN :startDate AND :endDate
                AND i.hotel.active = true
           GROUP BY i.hotel
           """)
     Page<HotelPriceDto> findHotelsWithAvailableInventory(
             @Param("city") String city,
             @Param("startDate") LocalDate startDate,
             @Param("endDate") LocalDate endDate,
             @Param("roomsCount") Integer roomsCount,
             @Param("dateCount") Long dateCount,
             Pageable pageable
     );

     Optional<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);
}
