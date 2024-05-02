package com.parcial.user_service.controller;

import com.parcial.user_service.dto.ClientDTO;
import com.parcial.user_service.dto.Response;
import com.parcial.user_service.exception.ClienteNoEncontradoException;
import com.parcial.user_service.models.Client;
import com.parcial.user_service.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Response<Client>> save(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Response<>("Cliente creado correctamente", clientService.save(clientDTO)));
    }

    @GetMapping
    public ResponseEntity<Response<List<Client>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", clientService.findAll()));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Response<ClientDTO>> findAll(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", clientService.findByDocumentNumber(name)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteById(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("Cliente eliminado correctamente", null));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e.getMessage()));
        }
    }
}
