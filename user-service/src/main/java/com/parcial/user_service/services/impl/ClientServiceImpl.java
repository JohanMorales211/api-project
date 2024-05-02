package com.parcial.user_service.services.impl;

import com.parcial.user_service.dto.ClientDTO;
import com.parcial.user_service.models.Client;
import com.parcial.user_service.repositories.ClientRepository;
import com.parcial.user_service.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new RuntimeException("El cliente con id: " + clientDTO.getDocumentNumber() + ", ya existe");
        }

        return clientRepository.save(factory(clientDTO));

    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientDTO findByDocumentNumber(String documentNumber) {
        Client client = clientRepository.findByDocumentNumber(documentNumber).orElse(null);
        ClientDTO clientDTO = new ClientDTO(client);
        return clientDTO;
    }

    @Override
    public Client update(ClientDTO clientDTO) {
        return clientRepository.save(factory(clientDTO));
    }

    @Override
    public Client factory(ClientDTO clientDTO) {
        Client nuevo = Client.builder()
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

        return nuevo;
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
}
