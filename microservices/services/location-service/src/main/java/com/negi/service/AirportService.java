package com.negi.service;

import java.util.List;

import com.negi.payload.request.AirportRequest;
import com.negi.payload.response.AirportResponse;

public interface AirportService {

    AirportResponse createAirport(AirportRequest request) throws Exception;

    AirportResponse getAirportById(Long id) throws Exception;

    List<AirportResponse> getAllAirport();

    AirportResponse updateAirport(Long id,AirportRequest request) throws Exception;

    void deleteAirport(Long id) throws Exception;

    List<AirportResponse> getAirportByCityId(Long cityId);
}
