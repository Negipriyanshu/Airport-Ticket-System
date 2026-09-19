package com.negi.payload.response;

import com.negi.embeddable.Support;
import com.negi.enums.AirlineStatus;

import jakarta.persistence.Embedded;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AirlineResponse {
    private Long id;

    private String iataCode;
    private String icaoCode;

    private String name;
    private String alias;

    private  String logoUrl;
    private String website;

    private AirlineStatus status;
    private String alliance;

    @Embedded
    private Support support;

    private Instant createdAt;
    private Instant updatedAt;

    private Long ownerId;
//    private UserDTO owner;
    private Long updatedById;

//    private Long headQuatersCityId;
//    private CityResponse headQuatersCity;





}
