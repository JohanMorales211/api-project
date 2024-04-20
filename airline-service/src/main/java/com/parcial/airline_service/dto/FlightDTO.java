package com.parcial.airline_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FlightDTO {

    private String airline;
    private String plate;
    private Date departureDate;
    private Date returnDate;
    private Boolean isDirect;
    private Integer durationHours;
    private Integer passengersNumber;
    private Long originId;
    private Long destinyId;

}
