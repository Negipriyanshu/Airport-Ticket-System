package com.negi.repository;

import com.negi.modal.FlightInstance;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;


public interface FlightInstanceRepository extends JpaRepository<FlightInstance,Long> {

    @Query("""
    SELECT fi FROM FlightInstance fi
    WHERE fi.airlineId =:airlineId
    AND (:departureAirportId is null or fi.departureAirportId = :departureAirportId)
    AND (:arrivalAirportId is null or fi.arrivalAirportId = :arrivalAirportId)
    AND (:flightId is null or fi.flight.id = :flightId)
    AND (:dayStart is null or fi.departureDateTime >= :dayStart)
    AND (:dayEnd is null or fi.arrivalDateTime <=:dayEnd)
""")
    Page<FlightInstance> findByAirlineId(@Param("airlineId") Long airlineId,
                                 @Param("departureAirportId") Long departureAirportId,
                                 @Param("arrivalAirportId") Long arrivalAirportId,
                                 @Param("flightId") Long flightId,
                                 @Param("dayStart") LocalDateTime dayStart,
                                 @Param("dayEnd") LocalDateTime dayEnd,
                                 Pageable pageable
                                 );
//    12/03/2026
//    00:00 dayStart
//    23:59 dayEnd

    boolean existsByFlightId(@NotNull(message ="Flight ID is required") Long flightId);
}
