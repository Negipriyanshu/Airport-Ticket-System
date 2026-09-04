package com.negi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.negi.payload.request.AirportRequest;
import com.negi.payload.response.AirportResponse;
import com.negi.payload.response.ApiResponse;
import com.negi.service.AirportService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {


    @Autowired
    private final AirportService airportService;
    
    @PostMapping()
    public ResponseEntity<AirportResponse> createAirport (
        @Valid @RequestBody AirportRequest request) throws Exception
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(airportService.createAirport(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById( @PathVariable Long id) throws Exception{
        return ResponseEntity
                .ok(airportService.getAirportById(id));
    }

    @GetMapping()
    public ResponseEntity<List<AirportResponse>> getAllAirport() {
        return ResponseEntity
                .ok(airportService.getAllAirport());
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<AirportResponse>> getAirportByCityId(@PathVariable Long cityId) {
        return ResponseEntity
                .ok(airportService.getAirportByCityId(cityId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(
        @PathVariable Long id, 
        @Valid @RequestBody AirportRequest request) throws Exception
        {
            return ResponseEntity.ok(airportService.updateAirport(id, request));
        }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception
    {
        airportService.deleteAirport(id);
        return ResponseEntity.ok(new ApiResponse("Airport deleted successfully..."));
    }

}
