package com.parcial.airline_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private String documentNumber;
    private String password;
    private String city;
    private String email;
    private String role;
}