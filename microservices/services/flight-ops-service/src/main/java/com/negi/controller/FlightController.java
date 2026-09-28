package com.negi.controller;

import com.negi.enums.FlightStatus;
import com.negi.payload.request.FlightRequest;
import com.negi.payload.response.FlightResponse;
import com.negi.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(
            @Valid @RequestBody FlightRequest request,
            @RequestHeader("Airline-Id") Long airlineId) throws Exception {

        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(airlineId,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(flightService.getFlightById(id));
    }

    @GetMapping("/airline")
    public ResponseEntity<Page<FlightResponse>> getFlightByAirline(
            @RequestHeader("Airline-Id") Long airlineId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            Pageable pageable){

        return ResponseEntity.ok(flightService.getFlightsByAirline(
                airlineId,
                departureAirportId,
                arrivalAirportId,
                pageable
        ));

    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(
            @PathVariable Long id,
            @RequestBody FlightRequest request)throws Exception{

        return ResponseEntity.ok(flightService.updateFlight(id,request));
    }


    @PatchMapping("/{id}/status")
    public ResponseEntity<FlightResponse> changeStatus(Long id, FlightStatus status) throws Exception{
        return ResponseEntity.ok(flightService.changeStatus(id,status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightResponse> deleteFlight(@PathVariable Long id,
                                                       @RequestHeader("Airline-Id") Long airlineId) throws Exception{
        flightService.deleteFlight(airlineId,id);
        return ResponseEntity.noContent().build();
    }

}
