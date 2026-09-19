package com.negi.service;

import com.negi.payload.request.AircraftRequest;
import com.negi.payload.response.AircraftResponse;

import java.util.List;

public interface AircraftService {

    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception;

    public AircraftResponse updateAircraft(Long id,AircraftRequest request, Long ownerId) throws Exception;

    public AircraftResponse getAircraftById(Long id) throws Exception;

    List<AircraftResponse> listAllAircraftByOwner(Long ownerId) throws Exception;

    void deleteAircraft(Long id,Long ownerId) throws Exception;
}
