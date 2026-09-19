package com.negi.payload.request;

import com.negi.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AirlineRequest {

    @NotBlank(message = "IATA code is mandatory")
    @Size(min=2,max = 2,message = "IATA code must be exactly 2 characters")
    private String iataCode;

    @NotBlank(message = "ICAO code is mandatory")
    @Size(min=3,max = 3,message = "ICAO code must be exactly 3 characters")
    private String icaoCode;

    @NotBlank(message = "AIRLINE name is mandatory")
    private String name;

    private String alias;

    private  String logoUrl;

    private String website;

    private AirlineStatus status;

    private String alliance;

    private Long headQuantersCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHours;



}
