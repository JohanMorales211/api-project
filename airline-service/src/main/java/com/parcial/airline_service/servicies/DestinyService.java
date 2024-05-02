package com.parcial.airline_service.servicies;


import com.parcial.airline_service.dto.DestinyDTO;
import com.parcial.airline_service.models.Destiny;

import java.util.List;

public interface DestinyService {

    public Destiny save(DestinyDTO destinyDTO);

    public Destiny findByName(String name);

    public List<Destiny> findAll();

    public Destiny update(DestinyDTO destinyDTO);

    public Destiny factory(DestinyDTO destinyDTO);

    void deleteById(Long id);
}
