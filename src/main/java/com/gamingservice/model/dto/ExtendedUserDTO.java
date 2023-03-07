package com.gamingservice.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ExtendedUserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal balance;
    private String gender;

    private String country;
    private String city;
    private String street;
}
