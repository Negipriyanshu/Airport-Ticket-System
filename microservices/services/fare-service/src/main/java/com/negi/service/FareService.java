package com.negi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.negi.payload.request.FareRequest;
import com.negi.payload.response.FareResponse;



@Service 

public interface FareService {   

    public FareResponse createFare(Long airlineId, FareRequest fareRequest) throws Exception ;

    public FareResponse updateFare(Long fareId, FareRequest fareRequest) throws Exception ;

    public FareResponse getFare(Long fareId) throws Exception ;

    public Page<FareResponse> getFaresByInstance(Long airlineId,Long flightInstanceId,Pageable pageable) throws Exception ;

    public void deleteFare(Long airlineId, Long fareId) throws Exception ; //soft delete

    public FareResponse getLowestFaresByInstance(Long flightInstanceId) throws Exception ;
    
}
