package com.negi.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max=100)
    private String name;

    @NotBlank(message = "City name is required")
    @Size(max=100)
    private String cityCode;

    @NotBlank(message = "Country name is required")
    @Size(max=100)
    private String countryName;

    @NotBlank(message = "Contry Code is required")
    @Size(max=5)
    private String countryCode;

    @Size(max=10)
    private String regionCode;

    @Size(max=10)
    private String timeZoneOffset;
}
