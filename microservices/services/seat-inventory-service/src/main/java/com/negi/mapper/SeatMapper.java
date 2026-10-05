package com.negi.mapper;

import java.util.List;

import com.negi.model.Seat;
import com.negi.model.SeatMap;
import com.negi.payload.response.SeatMapResponse;
import com.negi.payload.response.SeatResponse;

public class SeatMapper {
    public static SeatMapResponse toSeatMapResponse(SeatMap map, List<Seat> seats) {
    return SeatMapResponse.builder()
            .id(map.getId())
            .flightInstanceId(map.getFlightInstanceId())
            .aircraftId(map.getAircraftId())
            .rows(map.getRows())
            .columns(map.getColumns())
            .seats(seats.stream().map(SeatMapper::toSeatResponse).toList())
            .build();
}

public static SeatResponse toSeatResponse(Seat seat) {
    return SeatResponse.builder()
            .id(seat.getId())
            .seatMapId(seat.getSeatMapId())
            .seatNumber(seat.getSeatNumber())
            .cabinClass(seat.getCabinClass())
            .seatType(seat.getSeatType())
            .status(seat.getStatus())
            .holdExpiresAt(seat.getHoldExpiresAt())
            .bookingId(seat.getBookingId())
            .priceAddon(seat.getPriceAddon())
            .build();
}
}
