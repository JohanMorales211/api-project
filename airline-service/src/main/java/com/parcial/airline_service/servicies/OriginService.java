package com.parcial.airline_service.servicies;

import com.parcial.airline_service.dto.OriginDTO;
import com.parcial.airline_service.models.Origin;

public interface OriginService {

    public Origin save(OriginDTO originDTO);

    Origin getOriginByName(String name);

    public Origin update(Long id, OriginDTO originDTO);

    public Origin factory(OriginDTO originDTO);
}
