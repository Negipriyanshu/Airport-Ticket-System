package com.negi.controller;

import com.negi.payload.request.FlightInstanceRequest;
import com.negi.service.FlightInstanceService;
import com.negi.payload.response.*;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RestController 
@RequestMapping ("/api/flight-instances")
@RequiredArgsConstructor 
public class FlightInstanceController {
    
    private final FlightInstanceService flightInstanceService;

    @PostMapping()
    public ResponseEntity<FlightInstanceResponse> createFlightInstance(
        @RequestHeader("X_Airline_Id") Long airlineId,
        @Valid @RequestBody FlightInstanceRequest request) throws Exception {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightInstanceService.createFlightInstance(airlineId,request));
        }
    @GetMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> getFlightInstanceById(
            @PathVariable Long id) throws Exception {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(flightInstanceService.getFlightInstanceById(id));
    }

    @GetMapping()
    public ResponseEntity<Page<FlightInstanceResponse>> getAirlineId(
            @RequestHeader("X_User_Id") Long userId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long flightId,
            @RequestParam(required = false) LocalDateTime onDate,
            Pageable pageable)
    {
        return ResponseEntity.ok(flightInstanceService.getByAirlineId(
                userId,
                departureAirportId, arrivalAirportId,
                flightId,
                onDate,
                pageable
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> updateFlightInstance(
            @PathVariable Long id,
            @RequestBody FlightInstanceRequest request) throws Exception {
        return ResponseEntity.ok(flightInstanceService.updateFlightInstance(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightInstance(@PathVariable Long id) throws Exception {
        flightInstanceService.deleteFlightInstance(id);
        ApiResponse res = new ApiResponse("Flight instance deleted");
        return ResponseEntity.ok(res);
    }


}
