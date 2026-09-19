package com.negi.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class Support {
    private String email;
    private String phone;
    private String hours;
}
