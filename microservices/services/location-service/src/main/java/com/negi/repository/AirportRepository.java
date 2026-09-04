package com.negi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.negi.model.Airport;


public interface AirportRepository extends JpaRepository<Airport,Long>{

    public  Optional<Airport> findByIataCode(String iataCode);

    public List<Airport> findByCityId(Long cityId);



}
