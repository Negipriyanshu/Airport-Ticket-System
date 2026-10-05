package com.negi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "seat_map")
@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class SeatMap{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Long flightInstanceId;

    @Column (nullable = false)
    private Long aircraftId;

    @Column (nullable = false)
    @Positive(message = "rows need to positive")
    private Integer rows;
    
    @Column (nullable = false)
    @Positive(message = "columns need to positive")
    private String columns;

}
