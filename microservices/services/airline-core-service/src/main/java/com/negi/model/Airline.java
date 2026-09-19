package com.negi.model;

import com.negi.embeddable.Support;
import com.negi.enums.AirlineStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)  // createddate and updatedate annotation
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String iataCode;

    @Column(nullable = false,unique = true)
    private String icaoCode;

    @Column(unique = true,nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    private String name; // airline_name

    private String alias;

    private  String logoUrl;

    private String website;

    @Enumerated(EnumType.STRING)
    private AirlineStatus status= AirlineStatus.ACTIVE;

    private String alliance;

    private Long headQuartersCityId;

    @Embedded
    private Support support;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    private Long updatedById;

}
