package com.negi.payload.response;

import java.math.BigDecimal;

import com.negi.enums.CabinClass;
import com.negi.enums.FareStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Builder  
public class FareResponse {

    private Long id;
    private Long flightInstanceId;
    private Long airlineId;
    private CabinClass cabinClass;
    private BigDecimal baseFare;
    private BigDecimal taxAmount;
    private BigDecimal totalFare;
    private String currency;
    private Boolean refundable;
    private Boolean changeAllowed;
    private FareStatus fareStatus;
    

}
