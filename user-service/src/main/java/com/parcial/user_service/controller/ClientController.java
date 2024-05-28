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

    @GetMapping("/{id}")
    public ResponseEntity<Response<ClientDTO>> findById(@PathVariable Long id) {
        try {
            ClientDTO clientDTO = clientService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new Response<>("", clientDTO));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Client>> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        try {
            Client updatedClient = clientService.update(id, clientDTO);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>("Cliente actualizado correctamente", updatedClient));
        } catch (ClienteNoEncontradoException e) {
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

    @GetMapping("/city/{city}")
    public ResponseEntity<Response<List<Client>>> findByCity(@PathVariable String city) {
        List<Client> clients = clientService.findByCity(city);
        if (clients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new Response<>("No se encontraron clientes en la ciudad especificada", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response<>("Clientes encontrados en la ciudad especificada", clients));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deleteById(@PathVariable Long id) {
        try {
            clientService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>("Cliente eliminado correctamente", null));
        } catch (ClienteNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response<>(e.getMessage(), null));
        }
    }
}
