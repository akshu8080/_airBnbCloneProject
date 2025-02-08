package com.codingshuttle.airBnb.airBnbCloneProject.DTO;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.Hotel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class RoomDto {

     private Long id;
     private String type;
     private BigDecimal basePrice;
     private String[] photos;
     private String[] amenities;
     private Integer totalCount;
     private Integer capacity;

}
