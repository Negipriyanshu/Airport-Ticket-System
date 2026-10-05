package com.negi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.negi.model.SeatMap;

public interface SeatMapRepository extends JpaRepository<SeatMap,Long>{

    boolean existByFlightInstanceId(Long flightInstanceId);
    
}
