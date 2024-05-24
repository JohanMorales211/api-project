package com.parcial.airline_service.servicies;

import com.parcial.airline_service.dto.FlightDTO;
import com.parcial.airline_service.models.Destiny;
import com.parcial.airline_service.models.Flight;
import com.parcial.airline_service.models.Origin;

import java.util.List;

public interface FlightService {

    public Flight save(FlightDTO flightDTO);

    public Flight findByPlate(String plate);

    List<Flight> findByDestinyName(String destinyName);

    Flight update(String plate, FlightDTO flightDTO);

    public Flight factory(FlightDTO flightDTO, Destiny destiny, Origin origin);

}
