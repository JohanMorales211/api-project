package com.parcial.airline_service.servicies.impl;

import com.parcial.airline_service.dto.DestinyDTO;
import com.parcial.airline_service.exceptions.DestinoNoEncontradoException;
import com.parcial.airline_service.models.Destiny;
import com.parcial.airline_service.reposotories.DestinyRepository;
import com.parcial.airline_service.servicies.DestinyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DestinyServiceImpl implements DestinyService {

    private final DestinyRepository destinyRepository;

    @Override
    public Destiny save(DestinyDTO destinyDTO) {
        Optional<Destiny> existingDestiny = destinyRepository.findByNameIgnoreCase(destinyDTO.getName());
        if (existingDestiny.isPresent()) {
            throw new RuntimeException("El destino con el nombre '" + destinyDTO.getName() + "' ya existe.");
        }

        Destiny newDestiny = factory(destinyDTO);
        newDestiny.setName(newDestiny.getName().toLowerCase());
        return destinyRepository.save(newDestiny);
    }

    @Override
    public Destiny getDestinationByName(String name) {
        Destiny destiny = destinyRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new DestinoNoEncontradoException("Destino no encontrado con el nombre: " + name));
        return destiny;
    }

    @Override
    public Destiny update(Long id, DestinyDTO destinyDTO) {
        Destiny existingDestiny = destinyRepository.findById(id)
                .orElseThrow(() -> new DestinoNoEncontradoException("Destiny no encontrado con id: " + id));

        existingDestiny.setName(destinyDTO.getName());
        existingDestiny.setDescription(destinyDTO.getDescription());

        return destinyRepository.save(existingDestiny);
    }

    @Override
    public Destiny factory(DestinyDTO destinyDTO) {
        Destiny nuevo = Destiny.builder()
                .name(destinyDTO.getName())
                .description(destinyDTO.getDescription())
                .build();
        return nuevo;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Destiny> optionalClient = destinyRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new RuntimeException("No se puede encontrar un cliente con el ID proporcionado: " + id);
        }

        destinyRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return destinyRepository.existsByNameIgnoreCase(name);
    }


}
