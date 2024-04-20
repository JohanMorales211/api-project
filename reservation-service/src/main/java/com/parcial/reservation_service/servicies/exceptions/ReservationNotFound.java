package com.parcial.reservation_service.servicies.exceptions;

public class ReservationNotFound extends RuntimeException{

    public ReservationNotFound(String message){
        super(message);
    }
}
