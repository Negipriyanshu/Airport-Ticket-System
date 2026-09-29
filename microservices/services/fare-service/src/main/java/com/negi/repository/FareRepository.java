package com.negi.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.negi.model.Fare;

public interface FareRepository extends JpaRepository<Fare, Long> {
    
    @Query("""
    SELECT f FROM Fare f
    WHERE f.flightInstanceId = :flightInstanceId
      AND f.fareStatus <> 'INACTIVE'
    """)
    Page<Fare> findActiveFaresByFlightInstanceId(
        @Param("flightInstanceId") Long flightInstanceId,
        Pageable pageable);

    @Query("""
        SELECT f FROM Fare f
        WHERE f.id = :id
        AND f.airlineId = :airlineId
        AND f.fareStatus <> 'INACTIVE'
    """)
    Optional<Fare> findByIdAndAirlineId(
        @Param("id") Long id,
        @Param("airlineId") Long airlineId);

    // @Query("""
    //         SELECT f FROM fare f
    //         WHERE f.flightInstanceId = :flightInstanceId
    //         ORDER BY f.baseFare ASC
    //         LIMIT 1
    //         """)
    @Query("""
    SELECT f FROM Fare f
    WHERE f.flightInstanceId = :flightInstanceId
      AND f.fareStatus <> 'INACTIVE'
    ORDER BY f.baseFare ASC
    """)
Optional<Fare> findLowestFareByFlightInstanceId(
        @Param("flightInstanceId") Long flightInstanceId,
        Pageable pageable);

}
