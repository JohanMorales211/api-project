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

@RestController
@RequestMapping("/api/destiny")
@AllArgsConstructor
public class DestinyController {
    private final DestinyServiceImpl destinyService;

    @PostMapping
    public ResponseEntity<Response<Destiny>> createDestination(@RequestBody DestinyDTO destinyDTO) {
        try {
            Destiny savedDestiny = destinyService.save(destinyDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response<>("Destino creado exitosamente", savedDestiny));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response<>(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Destiny>> update(@PathVariable Long id, @RequestBody DestinyDTO destinyDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>("Destino actualizado correctamente", destinyService.update(id, destinyDTO)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<Destiny>> getDestinationByName(@PathVariable("name") String name) {
        Destiny destiny = destinyService.getDestinationByName(name);
        return ResponseEntity.ok(new Response<>("Destino encontrado exitosamente", destiny));
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
