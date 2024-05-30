package com.parcial.reservation_service.servicies;

import com.parcial.reservation_service.dto.ReservationPostDTO;
import com.parcial.reservation_service.models.Reservation;

import java.util.List;

public interface ReservationService {

    public Reservation save(ReservationPostDTO reservationPostDTO);

    public List<Reservation> findByUserDocumentNumber(String user);

    public List<Reservation> findAll();

    public Reservation findById(Integer id);

    public Integer update(Integer reservationId, ReservationPostDTO reservationPostDTO);

    public void validateHost(String hostName);

    public void validateUser(String userDocumentNumber);

    public void validateFlight(String flightPlate);

    void validateDestination(String destination);

}
