package com.codingshuttle.airBnb.airBnbCloneProject.DTO;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Room;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDto hotel;
    private List<RoomDto> rooms;
}