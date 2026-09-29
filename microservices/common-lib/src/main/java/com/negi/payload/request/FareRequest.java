package com.negi.payload.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.negi.enums.CabinClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class FareRequest {

    private Long flightInstanceId;
    private CabinClass cabinClass;
    private BigDecimal baseFare;
    private String currency;
    private BigDecimal taxAmount;
    private BigDecimal discountPercent;
    private Integer seatAllocated;
    private Boolean refundable;
    private Boolean changeAllowed;
    private LocalDateTime validFrom = LocalDateTime.now();
    private LocalDateTime validTo = LocalDateTime.now().plusDays(30);
    
}
