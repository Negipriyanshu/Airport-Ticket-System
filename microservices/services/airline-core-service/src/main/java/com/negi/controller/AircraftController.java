package com.negi.controller;

import com.negi.payload.request.AircraftRequest;
import com.negi.payload.response.AircraftResponse;
import com.negi.service.AircraftService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircrafts")
public class AircraftController {

    private final AircraftService aircraftService;

    @PostMapping
    public ResponseEntity<AircraftResponse> createAirport(
            @Valid @RequestBody AircraftRequest request,
            @RequestHeader("X-User-Id") Long userId
            ) throws Exception {
        AircraftResponse aircraft=aircraftService.createAircraft(request,userId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(aircraft);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftResponse> getAircraftById(
            @PathVariable Long id) throws Exception{

        return ResponseEntity.ok(aircraftService.getAircraftById(id));
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponse>> listAllAircraft(
            @RequestHeader("X-User-Id") Long userId) throws Exception{

        return ResponseEntity.ok(aircraftService.listAllAircraftByOwner(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AircraftResponse> updateAircraft(
            @PathVariable Long id,
            @RequestBody AircraftRequest request,
            @RequestHeader("X-User-Id") Long userId) throws Exception{

        return ResponseEntity.ok(aircraftService.updateAircraft(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @RequestHeader("X-User_Id") Long userId
    ) throws Exception{
        aircraftService.deleteAircraft(id,userId);
        return ResponseEntity
                .noContent()
                .build();
    }

}
