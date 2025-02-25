package com.codingshuttle.airBnb.airBnbCloneProject.DTO;

import com.codingshuttle.airBnb.airBnbCloneProject.Entity.User;
import com.codingshuttle.airBnb.airBnbCloneProject.Entity.enums.Gender;
 import lombok.Data;

@Data
public class GuestDto {
     private Long id;
     private User user;
     private String name;
     private Gender gender;
     private Integer age;
}

