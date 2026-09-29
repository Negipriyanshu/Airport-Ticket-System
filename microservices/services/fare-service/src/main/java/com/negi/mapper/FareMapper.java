package com.negi.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.negi.enums.FareStatus;
import com.negi.model.Fare;
import com.negi.payload.request.FareRequest;
import com.negi.payload.response.FareResponse;

public class FareMapper {
    
    public static Fare toFare(Long airlineId, FareRequest fareRequest) {
        return Fare.builder()
            .flightInstanceId(fareRequest.getFlightInstanceId())
            .airlineId(airlineId)
            .cabinClass(fareRequest.getCabinClass())
            .baseFare(fareRequest.getBaseFare())
            .currency(fareRequest.getCurrency()) 
            .taxAmount(fareRequest.getTaxAmount())
            .discountPercent(fareRequest.getDiscountPercent())
            .seatAllocated(fareRequest.getSeatAllocated())
            .refundable(fareRequest.getRefundable())
            .changeAllowed(fareRequest.getChangeAllowed())
            .validFrom(LocalDateTime.now())
            .validTo(LocalDateTime.now().plusDays(30))
            .fareStatus(FareStatus.ACTIVE)
            .build();
    }

    public static FareResponse toFareResponse(Fare fare) {
        return FareResponse.builder()
            .id(fare.getId())
            .flightInstanceId(fare.getFlightInstanceId())
            .airlineId(fare.getAirlineId())
            .cabinClass(fare.getCabinClass())
            .baseFare(fare.getBaseFare())
            .currency(fare.getCurrency())
            .taxAmount(fare.getTaxAmount())
            .totalFare(fare.getBaseFare().add(fare.getTaxAmount())
                        .subtract(fare.getBaseFare().add(fare.getTaxAmount())
                        .multiply(fare.getDiscountPercent())
                        .divide(BigDecimal.valueOf(100)))
            )
            .refundable(fare.getRefundable())
            .changeAllowed(fare.getChangeAllowed())
            .fareStatus(fare.getFareStatus())
            .build();
    }

    public static void updateFare(FareRequest fareRequest,Fare fare) {
        if(fareRequest ==null || fare ==null) {
            return ;
        }

        if(fareRequest.getFlightInstanceId() !=null) {
            fare.setFlightInstanceId(fareRequest.getFlightInstanceId());
        }

        if(fareRequest.getCabinClass() !=null) {
            fare.setCabinClass(fareRequest.getCabinClass());
        }

        if(fareRequest.getBaseFare() !=null) {
            fare.setBaseFare(fareRequest.getBaseFare());
        }

        if(fareRequest.getCurrency() !=null) {
            fare.setCurrency(fareRequest.getCurrency());
        }

        if(fareRequest.getTaxAmount() !=null) {
            fare.setTaxAmount(fareRequest.getTaxAmount());
        }

        if(fareRequest.getDiscountPercent() !=null) {
            fare.setDiscountPercent(fareRequest.getDiscountPercent());
        }
        
        if(fareRequest.getSeatAllocated() !=null) {
            fare.setSeatAllocated(fareRequest.getSeatAllocated());
        }

        if(fareRequest.getRefundable() !=null) {
            fare.setRefundable(fareRequest.getRefundable());
        }
        
        if(fareRequest.getChangeAllowed() !=null) {
            fare.setChangeAllowed(fareRequest.getChangeAllowed());
        }

        if(fareRequest.getValidFrom() !=null) {
            fare.setValidFrom(fareRequest.getValidFrom());
        }
        
        if(fareRequest.getValidTo() !=null) {
            fare.setValidTo(fareRequest.getValidTo().plusDays(30));
        }

        
        
    }
}
