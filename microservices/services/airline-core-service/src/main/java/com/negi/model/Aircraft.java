package com.negi.model;

import com.negi.enums.AircraftStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false)
    private String model;

    private String manufacturer;

    @Column(nullable = false)
    private Integer seatingCapacity;

    @Column(name = "economy_seats")
    private Integer economySeats;

    @Column(name = "premium_economy_seats")
    private Integer premiumEconomySeats;

    @Column(name = "business_seats")
    private Integer businessSeats;

    @Column(name = "first_class_seats")
    private Integer firstClassSeats;

    @Column(name = "cruising_speed_kmh")
    private Integer cruisingSpeedKmh;

    @Column(name = "range_km")
    private Integer rangeKm;

    @Column(name = "maximum_altitude_ft")
    private Integer maxAltitudeFt;

    @Column(name = "year_of_manufacture")
    private Integer yearOfManufacture;

    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false,length = 20)
    private AircraftStatus status =AircraftStatus.ACTIVE;

    private Boolean isAvailable =true;

    @ManyToOne
    private Airline airline;

    private Long currentAirportId;

    @CreatedDate
    @Column(name = "created_at", updatable = false,nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public Integer getTotalSeats(){
        return economySeats
                +premiumEconomySeats
                +businessSeats
                +firstClassSeats;
    }

    public boolean isOperational(){
        return AircraftStatus.ACTIVE.equals(status)
                && Boolean.TRUE.equals(isAvailable);
    }

    public boolean requiresMaintenance(){
        return nextMaintenanceDate!=null
                && nextMaintenanceDate.isBefore(LocalDate.now().plusWeeks(2));
    }
}
