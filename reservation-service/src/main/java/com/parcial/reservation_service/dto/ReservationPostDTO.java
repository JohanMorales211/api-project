package com.parcial.reservation_service.dto;

import java.time.LocalDate;

public record ReservationPostDTO(
        String hostName,
        String userDocumentNumber,
        String flightPlate,
        String destination,
        LocalDate checkIn,
        LocalDate checkOut,
        String paymentMethod) {
}