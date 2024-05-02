package com.parcial.airline_service.servicies.impl;

import com.parcial.airline_service.dto.DestinyDTO;
import com.parcial.airline_service.models.Destiny;
import com.parcial.airline_service.reposotories.DestinyRepository;
import com.parcial.airline_service.servicies.DestinyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DestinyServiceImpl implements DestinyService {

    private final DestinyRepository destinyRepository;
    @Override
    public Destiny save(DestinyDTO destinyDTO){

        Optional<Destiny> guardado = destinyRepository.findByName(destinyDTO.getName());

        if(guardado.isPresent()){
            throw new RuntimeException("El destino con el nombre"+destinyDTO.getName()+" ya existe");
        }

        return destinyRepository.save(factory(destinyDTO));
    }
    @Override
    public Destiny findByName(String name){
        return destinyRepository.findByName(name).orElse(null);
    }
    @Override
    public List<Destiny> findAll(){
        return destinyRepository.findAll();
    }
    @Override
    public Destiny update(DestinyDTO destinyDTO){
        return destinyRepository.save( factory(destinyDTO) );
    }
    @Override
    public Destiny factory(DestinyDTO destinyDTO){

        Destiny nuevo = Destiny.builder()
                .name(destinyDTO.getName())
                .description(destinyDTO.getDescription())
                .build();
        return nuevo;
    }

    @Override
    public void deleteById(Long id) {
        // Verificar si el cliente existe
        Optional<Destiny> optionalClient = destinyRepository.findById(id);
        if (optionalClient.isEmpty()) {
            throw new RuntimeException("No se puede encontrar un cliente con el ID proporcionado: " + id);
        }

        destinyRepository.deleteById(id);
    }
}
