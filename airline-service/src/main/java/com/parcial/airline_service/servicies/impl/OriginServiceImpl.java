package com.parcial.airline_service.servicies.impl;

import com.parcial.airline_service.dto.DestinyDTO;
import com.parcial.airline_service.dto.OriginDTO;
import com.parcial.airline_service.exceptions.DestinoNoEncontradoException;
import com.parcial.airline_service.exceptions.OriginNoEncontradoException;
import com.parcial.airline_service.models.Destiny;
import com.parcial.airline_service.models.Origin;
import com.parcial.airline_service.reposotories.OriginRepository;
import com.parcial.airline_service.servicies.OriginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OriginServiceImpl implements OriginService {

    private final OriginRepository originRepository;

    @Override
    public Origin save(OriginDTO originDTO) {

        Optional<Origin> guardado = originRepository.findByName(originDTO.getName());

        if (guardado.isPresent()) {
            throw new RuntimeException("El origen con el nombre " + originDTO.getName() + " ya existe");
        }

        return originRepository.save(factory(originDTO));
    }

    @Override
    public Origin findByName(String name) {
        return originRepository.findByName(name).orElse(null);
    }

    @Override
    public List<Origin> findAll() {
        return originRepository.findAll();
    }

    @Override
    public Origin update(Long id, OriginDTO originDTO) {
        Origin existingOrigin = originRepository.findById(id)
                .orElseThrow(() -> new OriginNoEncontradoException("Origen no encontrado con id: " + id));

        existingOrigin.setName(originDTO.getName());
        existingOrigin.setDescription(originDTO.getDescription());

        return originRepository.save(existingOrigin);
    }

    @Override
    public Origin factory(OriginDTO originDTO) {

        Origin nuevo = Origin.builder()
                .name(originDTO.getName())
                .description(originDTO.getDescription())
                .build();

        return nuevo;
    }

    public void deleteById(Long id) {
        // Verificar si el cliente existe
        Optional<Origin> optionalClient = originRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new RuntimeException("No se puede encontrar un origen con el ID proporcionado: " + id);
        }

        originRepository.deleteById(id);
    }
}
