package com.parcial.airline_service.controller;

import com.parcial.airline_service.dto.OriginDTO;
import com.parcial.airline_service.dto.Response;
import com.parcial.airline_service.exceptions.DestinoNoEncontradoException;
import com.parcial.airline_service.exceptions.OriginNoEncontradoException;
import com.parcial.airline_service.models.Origin;
import com.parcial.airline_service.servicies.impl.OriginServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/origin")
@AllArgsConstructor
public class OriginController {

    private final OriginServiceImpl originService;

    @PostMapping
    public ResponseEntity<Response<Origin>> save(@RequestBody OriginDTO originDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body( new Response<>("Origen creado correctamente", originService.save(originDTO)) );
    }

    @GetMapping
    public ResponseEntity<Response<List<Origin>>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>("", originService.findAll()) );
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<Origin>> findAll(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body( new Response<>("", originService.findByName(name)) );
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
