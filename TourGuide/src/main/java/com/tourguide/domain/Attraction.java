package com.tourguide.domain;

import lombok.*;

/**
 * The type Attraction.
 */
@Value

@Data
@Builder
@Getter
@AllArgsConstructor
public class Attraction {
    private String attractionName;
    private String city;
    private String state;
    private Location location;

}
