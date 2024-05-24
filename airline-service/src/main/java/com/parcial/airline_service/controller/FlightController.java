package com.parcial.airline_service.controller;

import com.parcial.airline_service.dto.FlightDTO;
import com.parcial.airline_service.dto.Response;
import com.parcial.airline_service.exceptions.ResourceNotFoundException;
import com.parcial.airline_service.models.Flight;
import com.parcial.airline_service.servicies.impl.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@AllArgsConstructor
public class FlightController {

    private final FlightServiceImpl flightService;

    @PostMapping
    public ResponseEntity<Response<Flight>> save(@RequestBody FlightDTO flightDTO) {
        try {
            Flight flight = flightService.save(flightDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response<>("Vuelo creado correctamente", flight));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>("Error interno del servidor", null));
        }
    }

    @GetMapping("/{plate}")
    public ResponseEntity<Response<Flight>> findByPlate(@PathVariable String plate) {
        Flight flight = flightService.findByPlate(plate);
        if (flight != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", flight));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("Vuelo no encontrado con la placa " + plate, null));
        }
    }

    @GetMapping("/destiny/{destinyName}")
    public ResponseEntity<Response<List<Flight>>> findByDestinyName(@PathVariable String destinyName) {
        List<Flight> flights = flightService.findByDestinyName(destinyName);
        if (!flights.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>("", flights));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("No se encontraron vuelos para el destino '" + destinyName + "'", null));
        }
    }

    @PutMapping("/{plate}")
    public ResponseEntity<Response<Flight>> update(@PathVariable String plate, @RequestBody FlightDTO flightDTO) {
        try {
            Flight updatedFlight = flightService.update(plate, flightDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>("Vuelo actualizado correctamente", updatedFlight));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>(e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response<>(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new Response<>("Error interno del servidor", null));
        }
    }
}
