package com.parcial.user_service.dto;

import com.parcial.user_service.models.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    public ClientDTO(Client client) {
        this.firstName = client.getFirstName();
        this.secondName =  client.getSecondName();
        this.firstSurname = client.getFirstSurname();
        this.secondSurname = client.getSecondSurname();
        this.documentNumber = client.getDocumentNumber();
        this.password = client.getPassword();
        this.city = client.getCity();
        this.email = client.getEmail();
        this.role = client.getRole();
    }
}
