package com.parcial.reservation_service.controller;

import com.parcial.reservation_service.dto.ReservationPostDTO;
import com.parcial.reservation_service.dto.Response;
import com.parcial.reservation_service.models.Reservation;
import com.parcial.reservation_service.servicies.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservation")
@AllArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Response<Reservation>> save(@RequestBody ReservationPostDTO ReservationPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>("Se ha registrado la reserva", reservationService.save(ReservationPostDTO)));
    }

    @GetMapping
    public ResponseEntity<Response<List<Reservation>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", reservationService.findAll()));
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Response<Integer>> update(@PathVariable Integer reservationId,
            @RequestBody ReservationPostDTO ReservationPostDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Se ha actualizado la reserva",
                reservationService.update(reservationId, ReservationPostDTO)));
    }

    @GetMapping("/Reservation/{userDocumentNumber}")
    public ResponseEntity<Response<List<Reservation>>> findByCodigoCliente(@PathVariable String userDocumentNumber) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>("", reservationService.findByUserDocumentNumber(userDocumentNumber)));
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Response<Reservation>> findById(@PathVariable Integer reservationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>("", reservationService.findById(reservationId)));
    }

}
