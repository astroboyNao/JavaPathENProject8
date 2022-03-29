package com.tourguide.infrastructure.repositories;

import com.tourguide.infrastructure.entities.Attraction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.tourguide.infrastructure.entities.VisitedLocation;

import java.util.UUID;

/**
 * The interface Gps repository.
 */
public interface GpsRepository {
    /**
     * Gets visited location for user.
     *
     * @param userUUID the user uuid
     * @return the visited location for user
     */
    Mono<VisitedLocation> getVisitedLocationForUser(UUID userUUID);

    /**
     * Gets attractions.
     *
     * @return the attractions
     */
    Flux<Attraction> getAttractions();
}
