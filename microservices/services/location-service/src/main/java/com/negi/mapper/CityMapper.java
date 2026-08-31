package com.negi.mapper;

import com.negi.model.City;
import com.negi.payload.request.CityRequest;
import com.negi.payload.response.CityResponse;

public class CityMapper {
    public static City toEntity(CityRequest request){
        if(request==null) return null;
        return City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .regionCode(request.getRegionCode())
                .timeZoneId(request.getTimeZoneOffset())
                .build();
    }

    public static CityResponse toCityResponse(City city){
        if(city == null) return null;

        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryCode(city.getCountryCode())
                .regionCode(city.getRegionCode())
                .build();
    }

    public static City updateEntity(City city, CityRequest request){

        if(request.getName()!=null) city.setName(request.getName());
        if (request.getCityCode()!=null) city.setCityCode(request.getCityCode());
        if(request.getCountryName()!=null) city.setCountryName(request.getCountryName());
        if(request.getRegionCode()!=null) city.setRegionCode(request.getRegionCode());

        return city;
    }



}
