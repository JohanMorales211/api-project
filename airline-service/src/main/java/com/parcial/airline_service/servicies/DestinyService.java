package com.parcial.airline_service.servicies;

import com.parcial.airline_service.dto.DestinyDTO;
import com.parcial.airline_service.models.Destiny;

public interface DestinyService {

    public Destiny save(DestinyDTO destinyDTO);

    Destiny getDestinationByName(String name);

    public Destiny update(Long id, DestinyDTO destinyDTO);

    public Destiny factory(DestinyDTO destinyDTO);

    boolean existsByName(String name);



    void deleteById(Long id);
}
