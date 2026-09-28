package com.negi.repository;

import com.negi.modal.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("""
    SELECT f FROM Flight f
    WHERE f.airlineId =:airlineId
    AND (:depId is null or f.departureAirportId = :depId)
    AND (:arrId is null or f.arrivalAirportId = :arrId)
""")
    Page<Flight> findByAirlineId(@Param("airlineId") Long airlineId,
                                 @Param("depId") Long depId,
                                 @Param("arrId") Long arrId,
                                 Pageable pageable);

    boolean existsByFlightNumberAndIdNot(String flightNumber, Long id);
    boolean existsByFlightNumber(String flightNumber);

    Optional<Flight> findByAirlineIdAndId(Long airlineId, Long id);
}
