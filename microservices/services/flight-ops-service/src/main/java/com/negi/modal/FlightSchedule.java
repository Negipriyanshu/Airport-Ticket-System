package com.negi.modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    @Column(nullable = false)
    private Long departureAirportId;
    private LocalTime departureTime;


    @Column(nullable = false)
    private Long arrivalAirportId;
    private LocalTime arrivalTime;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> operatingDays;
//    11/03/2026 to 10/04/2026
//    mon, tue, wed, thr

    private Boolean isActive=true;
}
