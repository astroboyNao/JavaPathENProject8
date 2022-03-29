package com.tourguide.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

/**
 * The type Location.
 */
@Data
@Builder
@AllArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
    private double longitude;
    private double latitude;

}
