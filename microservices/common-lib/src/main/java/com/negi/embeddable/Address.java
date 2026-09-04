package com.negi.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String postalCode;


}
