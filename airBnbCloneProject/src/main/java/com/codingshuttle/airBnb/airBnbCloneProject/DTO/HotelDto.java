package com.codingshuttle.airBnb.airBnbCloneProject.DTO;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {
     private Long Id;
     private String name;
     private String city;
     private String[] photos;
     private String[] amenities;
     private HotelContactInfo contactInfo;
     private boolean active;
 }
