package com.parcial.user_service.services.impl;

import com.parcial.user_service.dto.ClientDTO;
import com.parcial.user_service.exception.ClienteNoEncontradoException;
import com.parcial.user_service.models.Client;
import com.parcial.user_service.repositories.ClientRepository;
import com.parcial.user_service.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    @Override
    public Client save(ClientDTO clientDTO) {
        Optional<Client> guardado = clientRepository.findByDocumentNumber(clientDTO.getDocumentNumber());
        if (guardado.isPresent()) {
            throw new RuntimeException(
                    "El cliente con número de documento: " + clientDTO.getDocumentNumber() + " ya existe");
        }

        return clientRepository.save(factory(clientDTO));
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientDTO findByDocumentNumber(String documentNumber) {
        Client client = clientRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(() -> new ClienteNoEncontradoException(
                        "Cliente no encontrado con número de documento: " + documentNumber));
        return new ClientDTO(client);
    }

    @Override
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con ID: " + id));
        return new ClientDTO(client);
    }

    @Override
    public Client update(Long id, ClientDTO clientDTO) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClienteNoEncontradoException("Cliente no encontrado con id: " + id));

        existingClient.setFirstName(clientDTO.getFirstName());
        existingClient.setSecondName(clientDTO.getSecondName());
        existingClient.setFirstSurname(clientDTO.getFirstSurname());
        existingClient.setSecondSurname(clientDTO.getSecondSurname());
        existingClient.setDocumentNumber(clientDTO.getDocumentNumber());
        existingClient.setPassword(clientDTO.getPassword());
        existingClient.setCity(clientDTO.getCity());
        existingClient.setEmail(clientDTO.getEmail());
        existingClient.setRole(clientDTO.getRole());

        return clientRepository.save(existingClient);
    }

    @Override
    public Client factory(ClientDTO clientDTO) {
        return Client.builder()
                .firstName(clientDTO.getFirstName())
                .secondName(clientDTO.getSecondName())
                .firstSurname(clientDTO.getFirstSurname())
                .secondSurname(clientDTO.getSecondSurname())
                .documentNumber(clientDTO.getDocumentNumber())
                .password(clientDTO.getPassword())
                .city(clientDTO.getCity())
                .email(clientDTO.getEmail())
                .role(clientDTO.getRole())
                .build();
    }

    @Override
    public List<Client> findByCity(String city) {
        try {
            return clientRepository.findByCity(city);
        } catch (DataAccessException e) {
            throw new ClienteNoEncontradoException("Error al buscar clientes por ciudad: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        // Verificar si el cliente existe
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new RuntimeException("No se puede encontrar un cliente con el ID proporcionado: " + id);
        }

        clientRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }
}
