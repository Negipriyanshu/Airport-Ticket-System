package com.negi.controller;

import com.negi.payload.request.CityRequest;
import com.negi.payload.response.ApiResponse;
import com.negi.payload.response.CityResponse;
import com.negi.service.CityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private final CityService cityService;

//    /api/cities
    @PostMapping()
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest cityRequest) throws Exception{
        CityResponse res =cityService.createCity(cityRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
//   /api/cities/id
    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@Valid @PathVariable Long id) throws Exception{
        CityResponse res =cityService.getCityByID(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }
//    /api/cities
    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue ="0") int page,
            @RequestParam(defaultValue ="20") int size,
            @RequestParam(defaultValue ="name") String sortBy,
            @RequestParam(defaultValue ="asc") String sortDirection) throws  Exception{

        Sort sort =Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable =PageRequest.of(page,size,sort);

        return ResponseEntity.ok(cityService.getAllCities(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(
            @PathVariable Long id,
            @Valid  @RequestBody CityRequest request) throws  Exception{
        return ResponseEntity.ok(cityService.updateCity(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(
            @PathVariable Long id) throws Exception{
        cityService.deleteCity(id);
        return ResponseEntity.ok(new ApiResponse("city deleted Successfully"));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CityResponse>> searchCities(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok(cityService.searchCities(keyword,pageable));
    }

    @GetMapping("/country/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(
            @PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size){
        Pageable pageable=PageRequest.of(page,size);
        return ResponseEntity.ok(cityService.searchCitiesByCountryCode(countryCode.toUpperCase(),pageable));
    }

    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> checkCityExists(@PathVariable String cityCode){
        return ResponseEntity.ok(cityService.cityExists(cityCode.toUpperCase()));
    }
}
