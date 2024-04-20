package com.parcial.airline_service.servicies;


import com.parcial.airline_service.dto.OriginDTO;
import com.parcial.airline_service.models.Origin;

import java.util.List;

public interface OriginService {

    public Origin save(OriginDTO originDTO);

    public Origin findByName(String name);

    public List<Origin> findAll();

    public Origin update(OriginDTO originDTO);

    public Origin factory(OriginDTO originDTO);
}
