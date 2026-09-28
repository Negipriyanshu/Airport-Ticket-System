package com.negi.service;

import com.negi.payload.request.FlightInstanceRequest;
import com.negi.payload.response.FlightInstanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface FlightInstanceService {

    FlightInstanceResponse createFlightInstance(Long userId, FlightInstanceRequest request) throws Exception;

    FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request) throws Exception;

    FlightInstanceResponse getFlightInstanceById(Long id) throws Exception;

    Page<FlightInstanceResponse> getByAirlineId(Long airlineId,
                                                Long departureAirportId,
                                                Long arrivalAirportId,
                                                Long flightId,
                                                LocalDateTime onDate,
                                                Pageable pageable);

    void deleteFlightInstance(Long id) throws Exception;

}
