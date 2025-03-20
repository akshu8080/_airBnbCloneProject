package com.codingshuttle.airBnb.airBnbCloneProject.DTO;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Guest;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.User;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.enums.BookingStatus;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
     private Long id;
     private Integer roomsCount;
     private LocalDate checkInDate;
     private LocalDate checkOutDate;
     private LocalDateTime createdAt;
     private LocalDateTime updatedAt;
     private BookingStatus bookingStatus;
     private Set<GuestDto> guests;
     private BigDecimal amount;
}