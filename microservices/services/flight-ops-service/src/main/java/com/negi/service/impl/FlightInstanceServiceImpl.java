package com.negi.service.impl;

import com.negi.mapper.FlightInstanceMapper;
import com.negi.modal.Flight;
import com.negi.modal.FlightInstance;
import com.negi.payload.request.FlightInstanceRequest;
import com.negi.payload.response.AircraftResponse;
import com.negi.payload.response.AirlineResponse;
import com.negi.payload.response.AirportResponse;
import com.negi.payload.response.FlightInstanceResponse;
import com.negi.repository.FlightInstanceRepository;
import com.negi.repository.FlightRepository;
import com.negi.service.FlightInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightInstanceRepository flightInstanceRepository;
    private final FlightRepository flightRepository;
    @Override
    public FlightInstanceResponse createFlightInstance(Long userId, FlightInstanceRequest request) throws Exception {
        
        if(request==null){
            return null;
        }

        // todo : watch airlineId (userid)
        Flight flight=flightRepository.findById(request.getFlightId())
                .orElseThrow(
                        ()-> new Exception("Flight not found")
        );
        // todo: service to service communication
//        dummy aircraft
        AircraftResponse aircraft = AircraftResponse.builder()
                .id(1L)
                .totalSeats(90)
                .build();


        FlightInstance flightInstance = FlightInstanceMapper.toEntity(request, flight);
        flightInstance.setTotalSeats(aircraft.getTotalSeats());
        flightInstance.setAvailableSeats(aircraft.getTotalSeats());

        flightInstanceRepository.save(flightInstance);

//        todo : cerate seat instances


        return convertToFlightInstanceResponse(flightInstance);
    }

    @Override
    public FlightInstanceResponse updateFlightInstance(Long id, FlightInstanceRequest request) throws Exception{

        FlightInstance existing  = flightInstanceRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight instance not found...")
        );

        FlightInstanceMapper.updateEntity(request, existing);

        return convertToFlightInstanceResponse(flightInstanceRepository.save(existing));
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {

        FlightInstance flightInstance = flightInstanceRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight instance not found")
        );

        return convertToFlightInstanceResponse(flightInstance);

    }

    @Override
    public Page<FlightInstanceResponse> getByAirlineId(Long airlineId,
                                                       Long departureAirportId,
                                                       Long arrivalAirportId,
                                                       Long flightId,
                                                       LocalDateTime onDate,
                                                       Pageable pageable) {

        // todo: watch airlineId

        LocalDateTime start = onDate!=null? onDate.toLocalDate().atStartOfDay():null;
        LocalDateTime end = onDate!=null? onDate.plusDays(1).toLocalDate().atStartOfDay() : null;
        
        return flightInstanceRepository.findByAirlineId(
                airlineId, 
                departureAirportId, 
                arrivalAirportId, 
                flightId, 
                start, end, 
                pageable)
                .map(fi-> convertToFlightInstanceResponse(fi));
    }
// 11L:59
    @Override
    public void deleteFlightInstance(Long id) throws Exception {
        FlightInstance existing = flightInstanceRepository.findById(id)
        .orElseThrow(
                ()-> new Exception("Flight instance not found")
        );

        flightInstanceRepository.delete(existing);
    }


    private FlightInstanceResponse convertToFlightInstanceResponse(FlightInstance flightInstance){
        // todo: service to service

        AirlineResponse airline = AirlineResponse.builder()
                .id(flightInstance.getAirlineId())
                .build();

        AirportResponse departureAirport = AirportResponse.builder()
                .id(flightInstance.getDepartureAirportId())
                .build();

        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flightInstance.getArrivalAirportId())
                .build();

        AircraftResponse aircraftResponse = AircraftResponse.builder()
                .id(flightInstance.getFlight().getAircraftId())
                .build();
        return FlightInstanceMapper.toResponse(
                flightInstance,
                aircraftResponse,
                airline,
                departureAirport,
                arrivalAirport
        );
    }
}
