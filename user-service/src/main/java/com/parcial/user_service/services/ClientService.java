package com.parcial.user_service.services;

import com.parcial.user_service.dto.ClientDTO;
import com.parcial.user_service.models.Client;

import java.util.List;

public interface ClientService {
    public Client save(ClientDTO clientDTO);

    public List<Client> findAll();

    public ClientDTO findByDocumentNumber(String documentNumber);

    public Client update(ClientDTO clientDTO);

    public void deleteById(Long id);

    public Client factory(ClientDTO clientDTO);
}
