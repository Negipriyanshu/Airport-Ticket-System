package com.negi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.negi.embeddable.Address;
import com.negi.embeddable.GeoCode;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(unique = true,nullable = true,length = 3)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name="time_zone_id",length=50)
    private String timeZone;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name ="city_id")
    private City city;

    @JsonIgnore
    @Transient      // we don't need this field in our DB
    public String getDetailedName(){
        if(city!=null && city.getCountryCode()!=null)
            return name.toUpperCase() + "/" + city.getCountryCode();
        return name.toUpperCase();
    }
}
