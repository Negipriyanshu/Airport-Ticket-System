package com.negi.payload.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class GenerateSeatMapRequest {
    
    @Column(nullable = false)
    private Long flightInstanceId;
    
    @Column (nullable = false)
    private Long aircraftId;

    // temporary until Feign fetches AircraftResponse
    @NotNull(message = "economySeats is required")
    @Positive(message = "economySeats must be positive")
    private Integer economySeats;
    
    private Integer premiumEconomySeats;
    private Integer businessSeats;
    private Integer firstClassSeats;    
}