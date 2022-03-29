package com.tourguide.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.AbstractMap;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Visited location.
 */
@Value
@Builder
@Getter
@AllArgsConstructor
public class VisitedLocation {
    private User user;
    private Location location;
    private Date timeVisited;
    private int proximityBuffer = 10;
    private int attractionProximityRange = 200;

    /**
     * Near attraction boolean.
     *
     * @param attraction the attraction
     * @return the boolean
     */
    public boolean nearAttraction(Attraction attraction) {
        return !(this.getLocation().getDistance(attraction.getLocation()) > proximityBuffer);
    }

    /**
     * Near attraction java . util . set.
     *
     * @param attractions the attractions
     * @return the java . util . set
     */
    public java.util.Set<AbstractMap.SimpleEntry<Attraction, VisitedLocation>> nearAttraction(List<Attraction> attractions) {
        return attractions.stream().filter(attraction -> nearAttraction(attraction)).map(attraction -> new AbstractMap.SimpleEntry<>(attraction, this)).collect(Collectors.toSet());
    }

    /**
     * Is within attraction proximity boolean.
     *
     * @param attraction the attraction
     * @return the boolean
     */
    public boolean isWithinAttractionProximity(Attraction attraction) {
        return !(this.getLocation().getDistance(attraction.getLocation()) > attractionProximityRange);
    }

}
