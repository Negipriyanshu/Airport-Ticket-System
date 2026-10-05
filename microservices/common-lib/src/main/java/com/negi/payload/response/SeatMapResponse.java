package com.negi.payload.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder 
public class SeatMapResponse {
    private Long id;
    private Long flightInstanceId;
    private Long aircraftId;
    private Integer rows;
    private String columns;
    private List<SeatResponse> seats;
}
