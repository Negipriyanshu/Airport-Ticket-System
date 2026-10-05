package com.negi.payload.response;

import java.time.LocalDateTime;
import java.util.List;

import com.negi.enums.SeatStatus;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder 
public class SeatHoldResponse {
    
    private String holdId;
    private Long flightInstanceId;
    private List<String> seats;
    private LocalDateTime expiredAt;
    private SeatStatus status = SeatStatus.HELD;
}
