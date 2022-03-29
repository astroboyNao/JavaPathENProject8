package com.tourguide.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tourguide.domain.User;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.Date;

/**
 * The type Visited location.
 */
@Data
@Builder
@Getter
@AllArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitedLocation {
    private User user;
    private Location location;
    private Date timeVisited;
    private int proximityBuffer = 10;
    private int attractionProximityRange = 200;

}
