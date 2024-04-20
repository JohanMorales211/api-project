package com.parcial.reservation_service.servicies;


import com.parcial.reservation_service.dto.ReservationPostDTO;
import com.parcial.reservation_service.models.Reservation;

import java.util.List;

public interface ReservationService {

    public Reservation save(ReservationPostDTO reservationPostDTO);

    public List<Reservation> findByUserId(Integer userId);

    public List<Reservation> findAll();

    public Reservation findById(Integer id);

    public Integer update(Integer reservationId, ReservationPostDTO reservationPostDTO);

    public void validateHost(Integer hostId);

    public void validateUser(Integer userId);

    public void validateFlight(Integer flightId);

}
