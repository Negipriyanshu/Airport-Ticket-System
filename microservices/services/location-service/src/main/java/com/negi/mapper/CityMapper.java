package com.negi.mapper;

import com.negi.model.City;
import com.negi.payload.request.CityRequest;
import com.negi.payload.response.CityResponse;

public class CityMapper {

    public static City toEntity(CityRequest request) {
        if (request == null) {
            return null;
        }

        return City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .countryName(request.getCountryName())
                .regionCode(request.getRegionCode())
                .timeZoneId(request.getTimeZoneOffset())
                .build();
    }

    public static CityResponse toCityResponse(City city) {
        if (city == null) {
            return null;
        }

        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryName(city.getCountryName())
                .countryCode(city.getCountryCode())
                .regionCode(city.getRegionCode())
                .timeZoneOffset(city.getTimeZoneId())
                .build();
    }

    public static City updateEntity(City city, CityRequest request) {
        if (city == null || request == null) {
            return city;
        }

        if (request.getName() != null) {
            city.setName(request.getName());
        }
        if (request.getCityCode() != null) {
            city.setCityCode(request.getCityCode());
        }
        if (request.getCountryCode() != null) {
            city.setCountryCode(request.getCountryCode());
        }
        if (request.getCountryName() != null) {
            city.setCountryName(request.getCountryName());
        }
        if (request.getRegionCode() != null) {
            city.setRegionCode(request.getRegionCode());
        }
        if (request.getTimeZoneOffset() != null) {
            city.setTimeZoneId(request.getTimeZoneOffset());
        }

        return city;
    }
}