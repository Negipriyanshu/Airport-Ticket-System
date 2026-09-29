package com.negi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.negi.enums.CabinClass;
import com.negi.enums.FareStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@Entity 
@Table (name = "fare_config")
public class Fare {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flightInstanceId;  //flight-Ops

    private Long airlineId; // airline-service

    @Enumerated(EnumType.STRING)
    private CabinClass cabinClass; //ECONOMY / PREMIUM_ECONOMY / BUSINESS / FIRST

    private BigDecimal baseFare;

    private String currency;

    private BigDecimal taxAmount;

    private BigDecimal discountPercent;
    private Integer seatAllocated; // inventory linked to fare bucket
    private Boolean refundable;
    private Boolean changeAllowed;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    @Enumerated(EnumType.STRING)
    private FareStatus fareStatus; // ACTIVE /EXPIRED / DISABLED / INACTIVE (soft-delete)
    

}
