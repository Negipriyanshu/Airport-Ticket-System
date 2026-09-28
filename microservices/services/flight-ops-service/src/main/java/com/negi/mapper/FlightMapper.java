package com.negi.mapper;

import com.negi.modal.Flight;
import com.negi.payload.request.FlightRequest;
import com.negi.payload.response.AircraftResponse;
import com.negi.payload.response.AirlineResponse;
import com.negi.payload.response.AirportResponse;
import com.negi.payload.response.FlightResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FlightMapper {


    public static Flight toEntity(FlightRequest request){
        if(request==null){
            return null;
        }
        return Flight.builder()
                .flightNumber(request.getFlightNumber())
                .aircraftId(request.getAircraftId())
                .departureAirportId(request.getDepartureAirportId())
                .arrivalAirportId(request.getArrivalAirportId())
                .status(request.getStatus())
                .build();
    }

    public static FlightResponse toResponse(Flight flight,
                                            AircraftResponse aircraft,
                                            AirlineResponse airline,
                                            AirportResponse departureAirport,
                                            AirportResponse arrivalAirport
    ){
        if(flight==null){
            return null;
        }
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(airline)
                .aircraft(aircraft)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .status(flight.getStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }

    public static Flight updateEntity(FlightRequest request, Flight existing){
        if(request==null || existing==null) {
            return null;
        }
        if(request.getFlightNumber()!=null) {
            existing.setFlightNumber(request.getFlightNumber());
        }
        if(request.getAirlineId()!=null) {
            existing.setAirlineId(request.getAirlineId());
        }
        if(request.getAircraftId()!=null){
            existing.setAircraftId(request.getAircraftId());
        }
        if(request.getDepartureAirportId()!=null){
            existing.setDepartureAirportId(request.getDepartureAirportId());
        }
        if(request.getArrivalAirportId()!=null){
            existing.setArrivalAirportId(request.getArrivalAirportId());
        }
        if(request.getStatus()!=null){
            existing.setStatus(request.getStatus());
        }

        return existing;
    }

}
