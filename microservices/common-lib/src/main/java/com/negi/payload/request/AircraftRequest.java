package com.negi.payload.request;

import com.negi.enums.AircraftStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AircraftRequest {

    @NotBlank(message = "Aircraft code is required...")
    private String code;

    @NotBlank(message = "Aircraft model is required")
    private String model;

    @NotBlank(message = "Aircraft model is required")
    private String manufacturer;

    @NotNull(message = "Seating capacity is required")
    @Positive(message = "Seating capacity must be positive")
    private Integer seatingCapacity;


    @Positive(message = "Economy Seating capacity must be positive")
    private Integer economySeats;

    @Positive(message = "Premium Economy Seating capacity must be positive")
    private Integer premiumEconomySeats;

    @Positive(message = "Business Seating capacity must be positive")
    private Integer businessSeats;

    @Positive(message = "First Class Seating capacity must be positive")
    private Integer firstClassSeats;

    @Positive(message = "Cruising speed must be positive")
    private Integer cruisingSpeedKmh;

    @Positive(message = "Maximum altitude must be positive")
    private Integer maxAltitudeFt;

    @Positive(message = "Range must be positive")
    private Integer rangeKm;

    @Positive(message = "Year of Manufacture must be positive")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;

    @NotNull(message = "Status is required")
    private AircraftStatus status;

    private Boolean isAvailable;
    private Long currentAirportId;


}
