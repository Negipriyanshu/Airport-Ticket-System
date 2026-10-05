package com.negi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.negi.enums.CabinClass;
import com.negi.enums.SeatStatus;
import com.negi.enums.SeatType;

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
@Builder 
@NoArgsConstructor 
@AllArgsConstructor 
@Entity 
@Table(name = "seat_table")
public class Seat {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long seatMapId;     // foreign key seatmap

    private String seatNumber; // A12

    @Enumerated(EnumType.STRING)
    private CabinClass cabinClass;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    
    @Enumerated(EnumType.STRING)
    private SeatStatus status;  //AVAILABLE,HELD,BOOKED,OCCUPIED,BLOCKED

    private LocalDateTime holdExpiresAt; 

    private Long bookingId ;

    private BigDecimal priceAddon;
}
