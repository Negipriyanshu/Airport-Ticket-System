package com.negi.repository;

import com.negi.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft,Long> {

    List<Aircraft> findByAirlineId(Long airlineId);

    Boolean existsByCode(String code);

    Aircraft findByIdAndAirlineId(Long id, Long airlineId);
}
