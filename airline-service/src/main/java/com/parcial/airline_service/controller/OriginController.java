package com.parcial.airline_service.controller;

import com.parcial.airline_service.dto.OriginDTO;
import com.parcial.airline_service.dto.Response;
import com.parcial.airline_service.exceptions.OriginNoEncontradoException;
import com.parcial.airline_service.models.Origin;
import com.parcial.airline_service.servicies.impl.OriginServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/origin")
@AllArgsConstructor
public class OriginController {

    private final OriginServiceImpl originService;

    @PostMapping
    public ResponseEntity<Response<Origin>> createOrigin(@RequestBody OriginDTO originDTO) {
        try {
            Origin savedOrigin = originService.save(originDTO);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new Response<>("Origen creado exitosamente", savedOrigin));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response<>(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Origin>> update(@PathVariable Long id, @RequestBody OriginDTO originDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>("Origen actualizado correctamente", originService.update(id, originDTO)));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<Origin>> getOriginByName(@PathVariable("name") String name) {
        Origin origin = originService.getOriginByName(name);
        return ResponseEntity.ok(new Response<>("Origen encontrado exitosamente", origin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteById(@PathVariable Long id) {
        try {
            originService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Origen eliminado correctamente", null));
        } catch (OriginNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e.getMessage()));
        }
    }
}
