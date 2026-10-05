package com.negi.service;

import java.util.List;

import com.negi.payload.request.GenerateSeatMapRequest;
import com.negi.payload.request.SeatHoldRequest;
import com.negi.payload.response.SeatHoldResponse;
import com.negi.payload.response.SeatMapResponse;

public interface SeatService {

    SeatMapResponse generateSeatMap(GenerateSeatMapRequest request) throws Exception;

    SeatMapResponse getSeatMap(Long flightInstanceId) throws Exception;

    SeatHoldResponse holdSeats(SeatHoldRequest request) throws Exception;

    // ApiResponse releaseSeats(SeatReleaseRequest request) throws Exception;
    // // or SeatHoldResponse if you want released seat details back

    // SeatHoldResponse confirmSeats(SeatConfirmRequest request) throws Exception;

    // List<SeatResponse> getAvailableSeats(Long flightInstanceId) throws Exception;
}
