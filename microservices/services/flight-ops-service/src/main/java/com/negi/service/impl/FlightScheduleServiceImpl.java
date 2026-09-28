package com.negi.service.impl;

import com.negi.enums.FlightStatus;
import com.negi.mapper.FlightInstanceMapper;
import com.negi.mapper.FlightScheduleMapper;
import com.negi.modal.Flight;
import com.negi.modal.FlightSchedule;
import com.negi.payload.request.FlightInstanceRequest;
import com.negi.payload.request.FlightScheduleRequest;
import com.negi.payload.response.AirportResponse;
import com.negi.payload.response.FlightScheduleResponse;
import com.negi.repository.FlightInstanceRepository;
import com.negi.repository.FlightRepository;
import com.negi.repository.FlightScheduleRepository;
import com.negi.service.FlightInstanceService;
import com.negi.service.FlightScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightScheduleServiceImpl implements FlightScheduleService {

    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightRepository flightRepository;
    private final FlightInstanceService flightInstanceService;

    @Override
    public FlightScheduleResponse createFlightSchedule(
            Long airlineId,
            FlightScheduleRequest request) throws Exception {
//        todo: watch for airlineId
        Flight flight = flightRepository.findById(request.getFlightId()).orElseThrow(
                ()-> new Exception("Flight not found")
        );

        if(request.getEndDate().isBefore(request.getStartDate())){
            throw new Exception("end Date is before start date");
        }

        FlightSchedule flightSchedule = FlightScheduleMapper.toEntity(request,flight);
        flightScheduleRepository.save(flightSchedule);

        List<DayOfWeek> operatingDays = flightSchedule.getOperatingDays();
        LocalDate startDate = flightSchedule.getStartDate();
        LocalDate endDate = flightSchedule.getEndDate();

        FlightInstanceRequest flightInstanceRequest = FlightInstanceRequest
                .builder()
                .flightId(flight.getId())
                .scheduleId(flightSchedule.getId())
                .departureAirportId(flight.getDepartureAirportId())
                .arrivalAirportId(flight.getArrivalAirportId())
                .status(FlightStatus.SCHEDULED)
                .build();

        for(LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)){
            if(operatingDays.contains(date.getDayOfWeek())) {

                // departure time
                flightInstanceRequest.setDepartureDateTime(
                        LocalDateTime.of(date, flightSchedule.getDepartureTime())
                );
                // arrival time
                flightInstanceRequest.setArrivalDateTime(
                        LocalDateTime.of(date, flightSchedule.getArrivalTime())
                );

                flightInstanceService.createFlightInstance(airlineId, flightInstanceRequest);
            }
        }


        return convertToFlightScheduleResponse(flightSchedule);
    }

    @Override
    public FlightScheduleResponse getFlightScheduleById(Long id) throws Exception {
        FlightSchedule flightSchedule = flightScheduleRepository.findById(id)
                .orElseThrow(
                        ()-> new Exception("Flight is not scheduled...")
                );
        return convertToFlightScheduleResponse(flightSchedule);
    }

    @Override
    public List<FlightScheduleResponse> getFlightScheduleByAirline(Long airlineId) {
//        todo: watch airlineId
        List<FlightSchedule> schedules = flightScheduleRepository.findByFlightAirlineId(airlineId);
        return schedules
                .stream()
                .map(this::convertToFlightScheduleResponse)
                .toList();
    }

    @Override
    public FlightScheduleResponse updateFlightSchedule(Long id, FlightScheduleRequest request) throws Exception {
        FlightSchedule existingSchedule = flightScheduleRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight schedule not found with id")
        );

        FlightScheduleMapper.updateEntity(request,existingSchedule);
        flightScheduleRepository.save(existingSchedule);
        return convertToFlightScheduleResponse(existingSchedule);
    }

    @Override
    public void deleteFlightSchedule(Long id) throws Exception {
        FlightSchedule existing = flightScheduleRepository.findById(id)
                .orElseThrow(
                        ()-> new Exception("Schedule flight not found")
                );
        flightScheduleRepository.delete(existing);
    }

    private FlightScheduleResponse convertToFlightScheduleResponse(FlightSchedule flightSchedule){
//        dummy
//        todo: service to service communication
        AirportResponse departureAirport = AirportResponse.builder()
                .id(flightSchedule.getDepartureAirportId())
                .build();

        AirportResponse arrivalAirport = AirportResponse.builder()
                .id(flightSchedule.getArrivalAirportId())
                .build();

        return FlightScheduleMapper.toResponse(
                flightSchedule,arrivalAirport,departureAirport
        );
    }
}
