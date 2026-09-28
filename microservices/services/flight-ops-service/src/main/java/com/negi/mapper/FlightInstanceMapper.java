package com.negi.mapper;

import com.negi.enums.FlightStatus;
import com.negi.modal.Flight;
import com.negi.modal.FlightInstance;
import com.negi.payload.request.FlightInstanceRequest;
import com.negi.payload.response.AircraftResponse;
import com.negi.payload.response.AirlineResponse;
import com.negi.payload.response.AirportResponse;
import com.negi.payload.response.FlightInstanceResponse;

public class FlightInstanceMapper {

    public static FlightInstance toEntity(FlightInstanceRequest request, Flight flight){
        return FlightInstance.builder()
                .airlineId(flight.getAirlineId())
                .flight(flight)
                .departureAirportId(request.getDepartureAirportId()!=null?request.getDepartureAirportId():null)
                .arrivalAirportId(request.getArrivalAirportId()!=null?request.getArrivalAirportId():null)
                .scheduleId(request.getScheduleId())
                .departureDateTime(request.getDepartureDateTime())
                .arrivalDateTime(request.getArrivalDateTime())
                // from aircraft
//                .totalSeats(request.getTotalSeats())
//                .availableSeats(request.getAvailableSeats())

                .status(FlightStatus.SCHEDULED)
                .minAdvanceBookingDays(request.getMinAdvanceBookingDays())
                .maxAdvanceBookingDays(request.getMaxAdvanceBookingDays())
                .isActive(request.getIsActive()!=null?request.getIsActive():true)
                .build();
    }

    public static FlightInstanceResponse toResponse(FlightInstance instance,
                                                    AircraftResponse aircraftResponse,
                                                    AirlineResponse airline,
                                                    AirportResponse departureAirport,
                                                    AirportResponse arrivalAirport){
        if(instance==null){
            return null;
        }
        return FlightInstanceResponse.builder()
                .id(instance.getId())
                .flightId(instance.getFlight()!=null?instance.getFlight().getId():null)
                .flightNumber(instance.getFlight()!=null?instance.getFlight().getFlightNumber():null)
                .airlineId(instance.getAirlineId())
                .airlineName(airline.getName())
                .airlineLogo(airline.getLogoUrl())
                .aircraftId(aircraftResponse.getId())
                .aircraftModal(aircraftResponse.getModel())
                .aircraftCode(aircraftResponse.getCode())
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDateTime(instance.getDepartureDateTime())
                .arrivalDateTime(instance.getArrivalDateTime())
                .formattedDuration(instance.getFormatedDuration())
                .totalSeats(instance.getTotalSeats())
                .availableSeats(instance.getAvailableSeats())
                .status(instance.getStatus())
                .minAdvanceBookingDays(instance.getMinAdvanceBookingDays())
                .maxAdvanceBookingDays(instance.getMaxAdvanceBookingDays())
                .isActive(instance.getIsActive())
                .build();
    }

    public static void updateEntity(FlightInstanceRequest request,FlightInstance existingInstance){
        if(request==null){
            return;
        }
        if(request.getDepartureAirportId()!=null) existingInstance.setDepartureAirportId(request.getDepartureAirportId());
        if(request.getArrivalAirportId()!=null) existingInstance.setArrivalAirportId(request.getArrivalAirportId());
        if(request.getTotalSeats()!=null) existingInstance.setTotalSeats(request.getTotalSeats());
        if(request.getAvailableSeats()!=null) existingInstance.setAvailableSeats(request.getAvailableSeats());
        if(request.getStatus()!=null) existingInstance.setStatus(request.getStatus());
        if(request.getMinAdvanceBookingDays()!=null) existingInstance.setMinAdvanceBookingDays(request.getMinAdvanceBookingDays());
        if(request.getMaxAdvanceBookingDays()!=null) existingInstance.setMaxAdvanceBookingDays(request.getMaxAdvanceBookingDays());
        if(request.getIsActive()!=null) existingInstance.setIsActive(request.getIsActive());
        if(request.getDepartureDateTime()!=null) existingInstance.setDepartureDateTime(request.getDepartureDateTime());
        if(request.getArrivalDateTime()!=null) existingInstance.setArrivalDateTime(request.getArrivalDateTime());

    }
}
