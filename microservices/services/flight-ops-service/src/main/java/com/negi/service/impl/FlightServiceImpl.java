package com.negi.service.impl;

import com.negi.enums.FlightStatus;
import com.negi.mapper.FlightMapper;
import com.negi.modal.Flight;
import com.negi.payload.request.FlightRequest;
import com.negi.payload.response.AircraftResponse;
import com.negi.payload.response.AirlineResponse;
import com.negi.payload.response.AirportResponse;
import com.negi.payload.response.FlightResponse;
import com.negi.repository.FlightRepository;
import com.negi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest request) throws Exception{
        // todo: watch airlineId

        if(flightRepository.existsByFlightNumber(request.getFlightNumber())){
            throw new Exception("Flight with flight number already exist...");
        }
        Flight flight = FlightMapper.toEntity(request);
        flight.setAirlineId(airlineId);

        flightRepository.save(flight);
        return convertToFlightResponse(flight);
    }

    @Override
    public Page<FlightResponse> getFlightsByAirline(Long airlineId,
                                                    Long departureAirportId,
                                                    Long arrivalAirportId,
                                                    Pageable pageable)
        {
                // todo: watch airlineId
        return flightRepository.findByAirlineId(airlineId,
                departureAirportId,
                arrivalAirportId,
                pageable).map(this::convertToFlightResponse);
    }

    @Override
    public FlightResponse getFlightById(Long id) throws Exception {
        Flight flight=flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not found with id")
        );
        return convertToFlightResponse(flight);
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest request) throws Exception {
        Flight flightExisting = flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not exist with id")
        );

        if(request.getFlightNumber()!=null &&
                flightRepository.existsByFlightNumberAndIdNot(request.getFlightNumber(),id)
        ){
            throw new Exception("Flight with id already exist");
        }

        Flight updateFlight = flightRepository.save(
                FlightMapper
                        .updateEntity(request,flightExisting));

        return convertToFlightResponse(updateFlight);
    }

    @Override
    public void deleteFlight(Long airlineId, Long id) throws Exception {
        // todo: watch airlineId
    Flight existing = flightRepository.findByAirlineIdAndId(airlineId,id).orElseThrow(
            ()-> new Exception("Flight not found with id")
    );
    flightRepository.delete(existing);
    }

    @Override
    public FlightResponse changeStatus(Long id, FlightStatus status) throws Exception {

        Flight existing = flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not found with id")
        );
        existing.setStatus(status);
        Flight updated = flightRepository.save(existing);
        return convertToFlightResponse(updated);
    }

    public FlightResponse convertToFlightResponse(Flight flight){
        // todo: service to service communication
        
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(flight.getAircraftId())
                .build();
        AirlineResponse airline = AirlineResponse.builder()
                .id(flight.getAirlineId())
                .build();
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flight.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flight.getArrivalAirportId())
                .build();

        return FlightMapper.toResponse(
                flight,
                aircraft,
                airline,
                departureAirport,
                arrivalAirport);
    }
}
