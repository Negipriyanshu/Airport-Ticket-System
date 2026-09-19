package com.negi.service.impl;

import com.negi.enums.AirlineStatus;
import com.negi.mapper.AirlineMapper;
import com.negi.model.Airline;
import com.negi.payload.request.AirlineRequest;
import com.negi.payload.response.AirlineDropdownItem;
import com.negi.payload.response.AirlineResponse;
import com.negi.repository.AirlineRepository;
import com.negi.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long ownerId) {
        Airline airline = AirlineMapper.toEntity(request,ownerId);
        Airline saveAirline = airlineRepository.save(airline);

        return AirlineMapper.toResponse(saveAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long ownerId) throws Exception{
        Airline airline =airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("Airline not found with ownerId "+ ownerId)
                );
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) throws  Exception{
        Airline airline =airlineRepository.findById(id)
                .orElseThrow(
                        ()-> new Exception("Airline not found with Id "+ id)
                );
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirline(Pageable pageable) {
        return airlineRepository.findAll(pageable).map(
                        AirlineMapper::toResponse
                );
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long ownerId) throws Exception{
        Airline existingAirline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("Airline not found by ownerId "+ownerId)
                );
        Airline updatedAirline = AirlineMapper.updateEntity(existingAirline,request);
        airlineRepository.save(updatedAirline);
        return AirlineMapper.toResponse(updatedAirline);
    }

    @Override
    public void deleteAirline(Long id, Long ownerId) throws Exception {
        Airline airline = airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()-> new Exception("Airline not found by ownerId "+ownerId)
                );
        airlineRepository.delete(airline);
    }

    @Override
    public AirlineResponse changeStatusByAdmin(Long airlineId, AirlineStatus status) throws Exception {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(
                        ()-> new Exception("airline not  found with Airline ID "+ airlineId)
                );
        airline.setStatus(status);
        Airline updatedAirline= airlineRepository.save(airline);
        return AirlineMapper.toResponse(updatedAirline);
    }

    @Override
    public List<AirlineDropdownItem> getAirlineDropdown()
    {
        return airlineRepository.findByStatus(AirlineStatus.ACTIVE)
                .stream()
                .map(a-> AirlineDropdownItem.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .iataCode(a.getIataCode())
                        .icaoCode(a.getIcaoCode())
                        .logoUrl((a.getLogoUrl()))
                        .build()).toList();
    }
}
