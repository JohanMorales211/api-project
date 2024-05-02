package com.parcial.airline_service.controller;

import com.parcial.airline_service.dto.DestinyDTO;
import com.parcial.airline_service.dto.Response;
import com.parcial.airline_service.exceptions.DestinoNoEncontradoException;
import com.parcial.airline_service.models.Destiny;
import com.parcial.airline_service.servicies.impl.DestinyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destiny")
@AllArgsConstructor
public class DestinyController {
    private final DestinyServiceImpl destinyService;

    @PostMapping
    public ResponseEntity<Response<Destiny>> save(@RequestBody DestinyDTO destinyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>("Destino creado correctamente", destinyService.save(destinyDTO)));
    }

    @GetMapping
    public ResponseEntity<Response<List<Destiny>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", destinyService.findAll()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<Destiny>> findAll(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", destinyService.findByName(name)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Destiny>> update(@PathVariable Long id, @RequestBody DestinyDTO destinyDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>("Destino actualizado correctamente", destinyService.update(id, destinyDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteById(@PathVariable Long id) {
        try {
            destinyService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Destino eliminado correctamente", null));
        } catch (DestinoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e.getMessage()));
        }
    }
}
