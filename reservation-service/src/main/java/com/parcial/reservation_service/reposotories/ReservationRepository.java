package com.parcial.reservation_service.reposotories;

import com.parcial.reservation_service.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "SELECT * FROM Reservation r WHERE r.userId = :userId", nativeQuery = true)
    List<Reservation> findByUserId(Integer userId);
}
