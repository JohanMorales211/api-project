package com.parcial.airline_service.reposotories;

import com.parcial.airline_service.models.Destiny;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinyRepository  extends JpaRepository<Destiny, Long> {
    Optional<Destiny> findByName(String name);

}
