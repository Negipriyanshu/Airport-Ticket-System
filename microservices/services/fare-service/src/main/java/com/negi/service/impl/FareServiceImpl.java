package com.negi.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.negi.enums.FareStatus;
import com.negi.mapper.FareMapper;
import com.negi.model.Fare;
import com.negi.payload.request.FareRequest;
import com.negi.payload.response.FareResponse;
import com.negi.repository.FareRepository;
import com.negi.service.FareService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    @Override 
    public FareResponse createFare(Long airlineId, FareRequest fareRequest) throws Exception {

        if(fareRequest ==null || airlineId ==null) {
            throw new Exception("Fare request cannot be null");
        }

        Fare savedFare = fareRepository.save(FareMapper.toFare(airlineId, fareRequest));
        
        return FareMapper.toFareResponse(savedFare);
    }

    @Override
    public FareResponse updateFare(Long fareId, FareRequest fareRequest) throws Exception {
        
        Fare existedFare = fareRepository.findById(fareId).orElseThrow(
            ()-> new Exception("Fare is not present")
        );
        
        FareMapper.updateFare( fareRequest,existedFare);
        fareRepository.save(existedFare);

        return FareMapper.toFareResponse(existedFare);
    }

    @Override
    public FareResponse getFare(Long fareId) throws Exception {
        Fare fare = fareRepository.findById(fareId).orElseThrow(
            ()-> new Exception("Fare is not present")
        );

        return FareMapper.toFareResponse(fare);
        
    }
    @Override
    public Page<FareResponse> getFaresByInstance(
        Long airlineId,
        Long flightInstanceId,
        Pageable pageable) throws Exception {

    return fareRepository
            .findActiveFaresByFlightInstanceId(flightInstanceId, pageable)
            .map(FareMapper::toFareResponse);
}
    
    @Override
    public void deleteFare(Long airlineId, Long fareId) throws Exception {
        // todo airlineId

        Fare fare = fareRepository.findByIdAndAirlineId(airlineId,fareId).orElseThrow(
            ()->new Exception("Fare not found with fare id and airline id")
        );
        fare.setFareStatus(FareStatus.INACTIVE);
        fareRepository.save(fare);
    }
    
    @Override
    public FareResponse getLowestFaresByInstance(Long flightInstanceId) throws Exception {
       Fare lowestFare =fareRepository.findLowestFareByFlightInstanceId(flightInstanceId,PageRequest.of(0, 1)  ).orElseThrow(
        ()-> new Exception("Fare with this Flight Instance ID is not present")
       );

       return FareMapper.toFareResponse(lowestFare);
       
    }
}
