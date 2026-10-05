package com.negi.payload.request;

import java.util.List;

import jakarta.persistence.Column;

public class SeatHoldRequest {

    @Column(nullable = false)
    private Long flightInstanceId;

    @Column(nullable = false)
    private List<String> seatNumbers;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long holdMinutes;
    
}
