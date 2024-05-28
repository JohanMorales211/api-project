package com.parcial.airline_service.controller;

import com.parcial.airline_service.dto.FlightDTO;
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
    public ResponseEntity<Flight> createFlight(@RequestBody FlightDTO flightDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.save(flightDTO));
    }

    @GetMapping("/{plate}")
    public ResponseEntity<Flight> getFlightByPlate(@PathVariable String plate) {
        Flight flight = flightService.findByPlate(plate);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/destiny/{destinyName}")
    public ResponseEntity<List<Flight>> getFlightsByDestinyName(@PathVariable String destinyName) {
        return ResponseEntity.ok(flightService.findByDestinyName(destinyName));
    }

    @PutMapping("/{plate}")
    public ResponseEntity<Flight> updateFlight(@PathVariable String plate, @RequestBody FlightDTO flightDTO) {
        try {
            Flight updatedFlight = flightService.update(plate, flightDTO);
            return ResponseEntity.ok(updatedFlight);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{plate}/assign-client/{clientId}")
    public ResponseEntity<Flight> assignClientToFlight(@PathVariable String plate, @PathVariable Long clientId) {
        try {
            Flight flight = flightService.assignClientToFlight(plate, clientId);
            return ResponseEntity.ok(flight);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
