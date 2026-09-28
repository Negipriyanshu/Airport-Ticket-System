package com.negi.controller;

import com.negi.payload.request.FlightScheduleRequest;
import com.negi.payload.response.ApiResponse;
import com.negi.payload.response.FlightScheduleResponse;
import com.negi.service.FlightScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flight-schedules")
public class FlightScheduleController {

    private final FlightScheduleService flightScheduleService;

    @PostMapping
    public ResponseEntity<FlightScheduleResponse> createFlightSchedule(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @Valid @RequestBody FlightScheduleRequest request) throws Exception {
//        todo: watch for airline id
        return ResponseEntity.ok(flightScheduleService.createFlightSchedule(airlineId,request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> getFlightScheduleById(
            @RequestHeader("X-Airline-Id") Long airlineId,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightScheduleService.getFlightScheduleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteFlightSchedule(@PathVariable Long id) throws Exception{

        flightScheduleService.deleteFlightSchedule(id);
        ApiResponse res = new ApiResponse("schedule removed success");
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> updateFlightSchedule(
            @PathVariable Long id,
            @RequestBody FlightScheduleRequest request) throws Exception {
        return ResponseEntity.ok(flightScheduleService.updateFlightSchedule(id,request));
    }

    @GetMapping
    public ResponseEntity<List<FlightScheduleResponse>> getFlightScheduleByAirline(@RequestHeader("X-Airline-Id") Long airlineId){
        return ResponseEntity.ok(flightScheduleService.getFlightScheduleByAirline(airlineId));
    }

}
