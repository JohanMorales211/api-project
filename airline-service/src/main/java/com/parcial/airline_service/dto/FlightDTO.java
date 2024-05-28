package com.parcial.airline_service.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class FlightDTO {

    private String airline;
    private String plate;
    private Date departureDate;
    private Date returnDate;
    private Boolean isDirect;
    private Integer durationHours;
    private Integer passengersNumber;
    private String originName;
    private String destinyName;
    private Integer economyPrice;
    private Integer businessPrice;
    private Map<Integer, Long> assignedSeats;

}
