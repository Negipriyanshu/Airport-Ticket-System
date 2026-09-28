package com.negi.payload.response;

import java.time.Instant;
import java.time.LocalDateTime;
import com.negi.enums.FlightStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class FlightResponse {
    private Long id;
    private String flightNumber;
    private AirlineResponse airline;
    private AircraftResponse aircraft;
    private AirportResponse departureAirport;
    private AirportResponse arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private FlightStatus status;
    private Double lowestPrice;
    private Integer totalAvailableSeats;
    private Instant createdAt;
    private Instant updatedAt;
}
