package com.negi.service.impl;

import com.negi.mapper.AircraftMapper;
import com.negi.model.Aircraft;
import com.negi.model.Airline;
import com.negi.payload.request.AircraftRequest;
import com.negi.payload.response.AircraftResponse;
import com.negi.repository.AircraftRepository;
import com.negi.repository.AirlineRepository;
import com.negi.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftServiceImpl implements AircraftService {

    private final AirlineRepository airlineRepository;
    private final AircraftRepository aircraftRepository;

    @Override
    public AircraftResponse createAircraft(AircraftRequest request, Long ownerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("airline not exist for this OwnerId "+ ownerId)
                );
        Aircraft aircraft = AircraftMapper.toEntity(request,airline);


        if(aircraftRepository.existsByCode(aircraft.getCode())){
            throw new Exception("Code already exist with another aircraft");
        }
        if(aircraft.getSeatingCapacity()< aircraft.getTotalSeats()){
            throw new Exception("seating capacity can't exceeds");
        }

        aircraftRepository.save(aircraft);

        return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public AircraftResponse getAircraftById(Long id) throws Exception{
         Aircraft aircraft =aircraftRepository.findById(id)
                .orElseThrow(
                        ()-> new Exception(" Aircraft is not exist with ID "+id)
                );
         return AircraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> listAllAircraftByOwner(Long ownerId) throws Exception{

        // get the airline by ownerId
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("this owner don't have airline")
                );
        List<Aircraft> aircrafts = aircraftRepository.findByAirlineId(airline.getId());
        return aircrafts
                .stream()
                .map(AircraftMapper::toResponse)
                .toList();

    }

    @Override
    public AircraftResponse updateAircraft(Long id,AircraftRequest request, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("this owner don't have airline")
                );
        Aircraft aircraft= aircraftRepository.findByIdAndAirlineId(id,airline.getId());

        if(aircraft==null)
        {
            throw new Exception("Aircraft not exist with id");
        }
        if(request.getCode()!=null
        && !aircraft.getCode().equals(request.getCode()) // both code are not equal
        && aircraftRepository.existsByCode(request.getCode())){ // exist by code
            throw  new Exception("code already exist with another aircraft");
        }

        AircraftMapper.updateEntity(request,aircraft);

        return AircraftMapper.toResponse(
                aircraftRepository.save(aircraft)
        );
    }

    @Override
    public void deleteAircraft(Long id, Long ownerId) throws Exception{
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("this owner don't have airline")
                );

        Aircraft aircraft= aircraftRepository.findByIdAndAirlineId(id,airline.getId());
        if(aircraft==null){
            throw new Exception("Aircraft not exist with Id");
        }
        aircraftRepository.delete(aircraft);
    }
}
