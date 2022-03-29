package com.tourguide.domain.service;

import com.tourguide.domain.User;
import com.tourguide.domain.VisitedLocation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.tourguide.domain.Attraction;

/**
 * The interface Gps.
 */
public interface Gps {
    /**
     * Gets user location.
     *
     * @param user the user
     * @return the user location
     */
    Mono<VisitedLocation> getUserLocation(User user);

    /**
     * Gets attractions.
     *
     * @return the attractions
     */
    Flux<Attraction> getAttractions();
}
