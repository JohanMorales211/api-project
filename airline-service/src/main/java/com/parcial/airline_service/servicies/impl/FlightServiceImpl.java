package com.parcial.airline_service.servicies.impl;

import com.parcial.airline_service.dto.FlightDTO;
import com.parcial.airline_service.exceptions.ResourceNotFoundException;
import com.parcial.airline_service.models.Destiny;
import com.parcial.airline_service.models.Flight;
import com.parcial.airline_service.models.Origin;
import com.parcial.airline_service.reposotories.DestinyRepository;
import com.parcial.airline_service.reposotories.FlightRepository;
import com.parcial.airline_service.reposotories.OriginRepository;
import com.parcial.airline_service.servicies.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Optional;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    private final OriginRepository originRepository;

    private final DestinyRepository destinyRepository;

    @Override
    public Flight save(FlightDTO flightDTO) {
        // Buscar el origen por nombre
        Origin origin = originRepository.findByName(flightDTO.getOriginName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Origen '" + flightDTO.getOriginName() + "' no encontrado"));

        // Buscar el destino por nombre
        Destiny destiny = destinyRepository.findByName(flightDTO.getDestinyName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Destino '" + flightDTO.getDestinyName() + "' no encontrado"));

        // Verificar si el vuelo ya existe
        Optional<Flight> guardado = flightRepository.findByPlate(flightDTO.getPlate());
        if (guardado.isPresent()) {
            throw new RuntimeException("El vuelo con la matrícula " + flightDTO.getPlate() + " ya existe");
        }

        return flightRepository.save(factory(flightDTO, destiny, origin));
    }

    @Override
    public Flight findByPlate(String plate) {
        return flightRepository.findByPlate(plate).orElse(null);
    }

    @Override
    public List<Flight> findByDestinyName(String destinyName) {
        return flightRepository.findByDestiny_Name(destinyName);
    }

    @Override
    public Flight update(String plate, FlightDTO flightDTO) {
        // Buscar el vuelo por la placa
        Flight existingFlight = flightRepository.findByPlate(plate)
                .orElseThrow(() -> new ResourceNotFoundException("Vuelo con la placa '" + plate + "' no encontrado"));

        // Buscar el origen por nombre
        Origin origin = originRepository.findByName(flightDTO.getOriginName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Origen '" + flightDTO.getOriginName() + "' no encontrado"));

        // Buscar el destino por nombre
        Destiny destiny = destinyRepository.findByName(flightDTO.getDestinyName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Destino '" + flightDTO.getDestinyName() + "' no encontrado"));

        // Actualizar los campos del vuelo existente
        existingFlight.setAirline(flightDTO.getAirline());
        existingFlight.setDepartureDate(flightDTO.getDepartureDate());
        existingFlight.setReturnDate(flightDTO.getReturnDate());
        existingFlight.setIsDirect(flightDTO.getIsDirect());
        existingFlight.setDurationHours(flightDTO.getDurationHours());
        existingFlight.setPassengersNumber(flightDTO.getPassengersNumber());
        existingFlight.setOrigin(origin);
        existingFlight.setDestiny(destiny);

        // Guardar los cambios
        return flightRepository.save(existingFlight);
    }

    @Override
    public Flight factory(FlightDTO flightDTO, Destiny destiny, Origin origin) {
        return Flight.builder()
                .airline(flightDTO.getAirline())
                .plate(flightDTO.getPlate())
                .departureDate(flightDTO.getDepartureDate())
                .returnDate(flightDTO.getReturnDate())
                .isDirect(flightDTO.getIsDirect())
                .durationHours(flightDTO.getDurationHours())
                .passengersNumber(flightDTO.getPassengersNumber())
                .destiny(destiny)
                .origin(origin)
                .build();
    }

}
