package com.codingshuttle.airBnb.airBnbCloneProject.DTO;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;

}
