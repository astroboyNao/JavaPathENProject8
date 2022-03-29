package com.tourguide.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

/**
 * The type Location.
 */
@Value
@Getter
@Builder
@AllArgsConstructor
public class Location {

    private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
    private double longitude;
    private double latitude;


    /**
     * Gets distance.
     *
     * @param location the location
     * @return the distance
     */
    public double getDistance(Location location) {
        double lat1 = Math.toRadians(this.getLatitude());
        double lon1 = Math.toRadians(this.getLongitude());
        double lat2 = Math.toRadians(location.getLatitude());
        double lon2 = Math.toRadians(location.getLongitude());

        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }
}
