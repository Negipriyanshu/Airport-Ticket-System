package com.negi.modal;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.negi.enums.FlightStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@AllArgsConstructor 
@NoArgsConstructor 
@Builder 
@Data 
@EntityListeners(AuditingEntityListener.class)
public class Flight {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false,unique = true)
    private String flightNumber;

    @Column (nullable = false)
    private Long airlineId;

    @Column(nullable = false)
    private Long aircraftId;

    @Column(nullable = false)
    private Long departureAirportId;

    @Column(nullable = false)
    private Long arrivalAirportId;

    @Enumerated(EnumType.STRING)
    private FlightStatus status =FlightStatus.SCHEDULED;
    
    @CreatedDate 
    @Column (updatable = false,nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column (updatable = true)
    private Instant updatedAt;
    
}
