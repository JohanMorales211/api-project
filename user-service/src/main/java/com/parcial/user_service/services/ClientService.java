package com.parcial.user_service.services;

import com.parcial.user_service.dto.ClientDTO;
import com.parcial.user_service.models.Client;

import java.util.List;

public interface ClientService {
    Client save(ClientDTO clientDTO);

    List<Client> findAll();

    ClientDTO findByDocumentNumber(String documentNumber);

    ClientDTO findById(Long id);

    Client update(Long id, ClientDTO clientDTO);

    void deleteById(Long id);

    Client factory(ClientDTO clientDTO);

    List<Client> findByCity(String city);
}
