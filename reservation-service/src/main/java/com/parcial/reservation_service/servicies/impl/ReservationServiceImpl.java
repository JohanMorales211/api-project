package com.parcial.reservation_service.servicies.impl;

import com.parcial.reservation_service.dto.*;
import com.parcial.reservation_service.models.Reservation;
import com.parcial.reservation_service.reposotories.ReservationRepository;
import com.parcial.reservation_service.servicies.ReservationService;
import com.parcial.reservation_service.servicies.exceptions.ReservationNotFound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final RestTemplate restTemplate;

    private ReservationRepository reservationRepository;

    @Override
    public Reservation save(ReservationPostDTO reservationPostDTO) {
        validateHost(reservationPostDTO.hostName());
        validateFlight(reservationPostDTO.flightPlate());
        validateUser(reservationPostDTO.userDocumentNumber());

        Reservation reservation = new Reservation();
        reservation.setFlight(reservationPostDTO.flightPlate());
        reservation.setHost(reservationPostDTO.hostName());
        reservation.setUserDocument(reservationPostDTO.userDocumentNumber());
        reservation.setReservationDate(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findByUserDocumentNumber(String user) {
        validateUser(user);
        List<Reservation> lista = reservationRepository.findByUserId(user);

        return new ArrayList<>();
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Integer id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public void validateFlight(String flightPlate) {
        ResponseEntity<Response<Flight>> responseEntity;
        try {

            responseEntity = restTemplate.exchange(
                    "http://gateway-service:8780/api/flights/" + flightPlate,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Response<Flight>>() {});

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                Flight flight = responseEntity.getBody().getDato();
                if(flight == null || flight.getId() == null){
                    throw new RuntimeException("El número " + flightPlate + " no pertenece a ningún flight");
                }
            }

        }catch (HttpClientErrorException e){
            // Manejar errores específicos del Flight HTTP
            throw new RuntimeException("Error en la solicitud: " + e.getMessage());
        }catch (HttpServerErrorException e){
            // Manejar errores del servidor
            throw new RuntimeException("Error en el servidor: " + e.getMessage());
        }catch (Exception e){
            // Manejar cualquier otra excepción
            e.printStackTrace();
            throw new RuntimeException("Hubo un error recuperando la información del Flight");
        }
    }


    @Override
    public void validateHost(String hostName) {
        ResponseEntity<Response<Host>> responseEntity;
        try {

            responseEntity = restTemplate.exchange(
                    "http://gateway-service:8780/api/hostings/" + hostName,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Response<Host>>() {});

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                Host host = responseEntity.getBody().getDato();
                if(host == null || host.getId() == null){
                    throw new RuntimeException("El nombre " + hostName + " no pertenece a ningún host");
                }
            }

        }catch (HttpClientErrorException e){
            // Manejar errores específicos del Flight HTTP
            throw new RuntimeException("Error en la solicitud: " + e.getMessage());
        }catch (HttpServerErrorException e){
            // Manejar errores del servidor
            throw new RuntimeException("Error en el servidor: " + e.getMessage());
        }catch (Exception e){
            // Manejar cualquier otra excepción
            e.printStackTrace();
            throw new RuntimeException("Hubo un error recuperando la información del host");
        }
    }

    @Override
    public void validateUser(String userDocumentNumber) {
        ResponseEntity<Response<User>> responseEntity;
        try {

            responseEntity = restTemplate.exchange(
                    "http://gateway-service:8780/api/client/" + userDocumentNumber,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<Response<User>>() {});

            if(responseEntity.getStatusCode() == HttpStatus.OK){
                User user = responseEntity.getBody().getDato();
                if(user == null || user.getDocumentNumber() == null){
                    throw new RuntimeException("El número de documento " + userDocumentNumber + " no pertenece a ningún usuario");
                }
            }

        }catch (HttpClientErrorException e){
            // Manejar errores específicos del Flight HTTP
            throw new RuntimeException("Error en la solicitud: " + e.getMessage());
        }catch (HttpServerErrorException e){
            // Manejar errores del servidor
            throw new RuntimeException("Error en el servidor: " + e.getMessage());
        }catch (Exception e){
            // Manejar cualquier otra excepción
            e.printStackTrace();
            throw new RuntimeException("Hubo un error recuperando la información del user");
        }

    }

    @Override
    public Integer update(Integer reservationId, ReservationPostDTO reservationPostDTO) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()-> new ReservationNotFound("No existe una reserva con el código: "+reservationId));

        validateHost(reservationPostDTO.hostName());
        validateFlight(reservationPostDTO.flightPlate());
        validateUser(reservationPostDTO.userDocumentNumber());

        reservation.setFlight(reservationPostDTO.flightPlate());
        reservation.setHost(reservationPostDTO.hostName());
        reservation.setUserDocument(reservationPostDTO.userDocumentNumber());
        reservation.setReservationDate(LocalDateTime.now());

        return reservationRepository.save(reservation).getId();
    }


}
