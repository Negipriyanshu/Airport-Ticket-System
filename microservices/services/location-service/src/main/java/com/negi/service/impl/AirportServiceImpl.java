package com.negi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.negi.mapper.AirportMapper;
import com.negi.model.Airport;
import com.negi.model.City;
import com.negi.payload.request.AirportRequest;
import com.negi.payload.response.AirportResponse;
import com.negi.repository.AirportRepository;
import com.negi.repository.CityRespository;
import com.negi.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

    private final CityRespository cityRepository;
    private final AirportRepository airportRepository;

    public AirportServiceImpl(
            CityRespository cityRepository,
            AirportRepository airportRepository) {
        this.cityRepository = cityRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public AirportResponse createAirport(AirportRequest request) throws Exception {
        if (airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with IATA code already exists");
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new Exception("City not found"));

        Airport airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        Airport savedAirport = airportRepository.save(airport);
        return AirportMapper.toAirportResponse(savedAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new Exception("Airport not found with ID: " + id));

        return AirportMapper.toAirportResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirport() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toAirportResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) throws Exception {
        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new Exception("Airport not found with ID: " + id));

        if (request.getIataCode() != null
                && !existingAirport.getIataCode().equals(request.getIataCode())
                && airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with IATA code already exists");
        }

        AirportMapper.updateEntity(existingAirport, request);

        if (request.getCityId() != null) {
            City city = cityRepository.findById(request.getCityId())
                    .orElseThrow(() -> new Exception("City not found"));
            existingAirport.setCity(city);
        }

        Airport updatedAirport = airportRepository.save(existingAirport);
        return AirportMapper.toAirportResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) throws Exception {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new Exception("Airport not found with ID: " + id));

        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toAirportResponse)
                .collect(Collectors.toList());
    }
}