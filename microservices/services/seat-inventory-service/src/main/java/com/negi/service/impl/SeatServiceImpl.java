package com.negi.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.negi.enums.CabinClass;
import com.negi.enums.SeatStatus;
import com.negi.enums.SeatType;
import com.negi.mapper.SeatMapper;
import com.negi.model.Seat;
import com.negi.model.SeatMap;
import com.negi.payload.request.GenerateSeatMapRequest;
import com.negi.payload.request.SeatHoldRequest;
import com.negi.payload.response.SeatHoldResponse;
import com.negi.payload.response.SeatMapResponse;
import com.negi.repository.SeatMapRepository;
import com.negi.repository.SeatRepository;
import com.negi.service.SeatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatMapRepository seatMapRepository;
    private final SeatRepository seatRepository;


    @Override
    public SeatMapResponse generateSeatMap(GenerateSeatMapRequest request) throws Exception {

    // Logic 
    // 1.Validate request not null for both IDs present.
    // 2.Reject if a map already exists for flightInstanceId (BRD: unique per instance).
    // 3.Load aircraft layout (Feign → airline-core): cabin counts (economySeats, etc.), capacity.
    // 4.Decide rows / columns (e.g. "ABCDEF") from capacity or fixed layout rules.
    // 5.Persist SeatMap.
    // 6.Generate Seat rows: for each row × column letter → seatNumber (12A), set cabinClass by row bands, seatType (WINDOW/AISLE/MIDDLE), status = AVAILABLE, priceAddon default/0.
    // 7.Bulk save seats; return map + seat list as SeatMapResponse.
    
        if(request==null
            || request.getFlightInstanceId()==null
            || request.getAircraftId()==null){
            throw new Exception("FlightInstance Id and Aircraft Id are required");
        }

        if(seatMapRepository.existByFlightInstanceId(request.getFlightInstanceId())){
            throw new Exception("Seat map already exists for flight instance: ");
        }
        
        // check class lvl seats is present or not (no. of seats = 0)
        int first = validSeat(request.getFirstClassSeats());
        int business = validSeat(request.getBusinessSeats());
        int premium = validSeat(request.getPremiumEconomySeats());
        int economy = validSeat(request.getEconomySeats());
        int totalSeats = first + business + premium + economy;

        if(totalSeats <=0){
            throw new Exception("Aircraft must have at least one seat");
        }

        final String columns = "ABCDEF";
        int seatsPerRow = columns.length();
        int rows = (int) Math.ceil((double) totalSeats / seatsPerRow);

        SeatMap seatMap = seatMapRepository.save(
            SeatMap.builder()
                    .flightInstanceId(request.getFlightInstanceId())
                    .aircraftId(request.getAircraftId())
                    .rows(rows)
                    .columns(columns)
                    .build()
        );  
        List<Seat> seats = new ArrayList<>();
        int seatIndex = 0; // 0 .. totalSeats-1 across cabin bands
        for (int row = 1; row <= rows && seatIndex < totalSeats; row++) {
            for (int col = 0; col < seatsPerRow && seatIndex < totalSeats; col++) {
                char letter = columns.charAt(col);
                String seatNumber = row + String.valueOf(letter); // e.g. 12A

                seats.add(Seat.builder()
                    .seatMapId(seatMap.getId())
                    .seatNumber(seatNumber)
                    .cabinClass(resolveCabinClass(seatIndex, first, business, premium, economy))
                    .seatType(resolveSeatType(letter, columns))
                    .status(SeatStatus.AVAILABLE)
                    .priceAddon(BigDecimal.ZERO)
                    .holdExpiresAt(null)
                    .bookingId(null)
                    .build());

                seatIndex++;
        }
    }   

    seatRepository.saveAll(seats);

    return SeatMapper.toSeatMapResponse(seatMap,seats);

    }

    

    @Override
    public SeatMapResponse getSeatMap(Long flightInstanceId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSeatMap'");
    }

    @Override
    public SeatHoldResponse holdSeats(SeatHoldRequest request) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'holdSeats'");
    }
    

    private static int validSeat(Integer value) {
    return value == null ? 0 : value;
    }

    /** Front → back: FIRST, BUSINESS, PREMIUM_ECONOMY, ECONOMY */
    private CabinClass resolveCabinClass(
        int seatIndex, 
        int first, 
        int business, 
        int premium, 
        int economy) {
    if (seatIndex < first) return CabinClass.FIRST;
    seatIndex -= first;
    if (seatIndex < business) return CabinClass.BUSINESS;
    seatIndex -= business;
    if (seatIndex < premium) return CabinClass.PREMIUM_ECONOMY;
    return CabinClass.ECONOMY;
    }

    /** A/F window, C/D aisle, B/E middle (for ABCDEF) */
    private SeatType resolveSeatType(char letter, String columns) {
        int idx = columns.indexOf(letter);
        int last = columns.length() - 1;
        if (idx == 0 || idx == last) return SeatType.WINDOW;
        if (idx == 2 || idx == 3) return SeatType.AISLE;   // C, D
    return SeatType.MIDDLE;                            // B, E
    }   
}


