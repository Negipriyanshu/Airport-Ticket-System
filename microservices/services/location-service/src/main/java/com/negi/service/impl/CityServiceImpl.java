package com.negi.service.impl;

import com.negi.mapper.CityMapper;
import com.negi.model.City;
import com.negi.payload.request.CityRequest;
import com.negi.payload.response.CityResponse;
import com.negi.repository.CityRespository;
import com.negi.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRespository cityRespository;

    @Override
    public CityResponse createCity(CityRequest request) throws Exception{
        if(cityRespository.existsByCityCode(request.getCityCode())) {
            throw new Exception("city with given code already exist");
        }

        City city = CityMapper.toEntity(request);
        City result = cityRespository.save(city);
        return CityMapper.toCityResponse(result);
    }

    @Override
    public CityResponse getCityByID(Long id) throws Exception {
        City city = cityRespository.findById(id).orElseThrow(
                ()-> new Exception("city not exist with given id")
        );
        return CityMapper.toCityResponse(city);

    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception{
        City city =cityRespository.findById(id).orElseThrow(
                ()-> new Exception("Resource not found exception")
        );
        if(cityRespository.existByCityCode(request.getCityCode())){
            throw new Exception("City with givven code already exist");
        }
        City updateCity = cityRespository.save(CityMapper.updateEntity(city,request));
        return CityMapper.toCityResponse(updateCity);
    }

    @Override
    public void deleteCity(Long id) throws Exception{
        City city =cityRespository.findById(id).orElseThrow(
                ()-> new Exception("Resource not found exception")
        );
        cityRespository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRespository.findAll(pageable).map(CityMapper::toCityResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRespository.searchByKeyword(keyword,pageable).map(CityMapper::toCityResponse);
    }

    @Override
    public Page<CityResponse> searchCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRespository.findByCountryCodeIgnoreCase(countryCode, pageable)
                .map(CityMapper::toCityResponse);
    }

    @Override
    public Boolean cityExists(String cityCode) {
        return cityRespository.existByCityCode(cityCode);
    }




}
