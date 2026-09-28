package com.negi.service;

import com.negi.payload.request.FlightScheduleRequest;
import com.negi.payload.response.FlightScheduleResponse;

import java.util.List;

public interface FlightScheduleService {

    FlightScheduleResponse createFlightSchedule(
            Long userId,
            FlightScheduleRequest request) throws Exception;

    FlightScheduleResponse getFlightScheduleById(Long id) throws Exception;

    List<FlightScheduleResponse> getFlightScheduleByAirline(Long userId);

    FlightScheduleResponse updateFlightSchedule(Long id,
                                                FlightScheduleRequest request) throws Exception;
    void deleteFlightSchedule(Long id) throws Exception;


}
