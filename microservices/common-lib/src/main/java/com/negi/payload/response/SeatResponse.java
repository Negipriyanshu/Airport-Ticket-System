package com.negi.payload.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.negi.enums.CabinClass;
import com.negi.enums.SeatStatus;
import com.negi.enums.SeatType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
public class SeatResponse {
    private Long id;
    private Long seatMapId;
    private String seatNumber;
    private CabinClass cabinClass;
    private SeatType seatType;
    private SeatStatus status;
    private LocalDateTime holdExpiresAt;
    private Long bookingId;
    private BigDecimal priceAddon;
}
