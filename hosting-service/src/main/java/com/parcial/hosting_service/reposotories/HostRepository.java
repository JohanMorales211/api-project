package com.parcial.hosting_service.reposotories;

import com.parcial.hosting_service.models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    Optional<Host> findByName(String name);

    List<Host> findByDestinyName(String destinyName);
}
